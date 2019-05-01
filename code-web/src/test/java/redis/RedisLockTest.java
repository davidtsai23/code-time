package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class RedisLockTest {
    @Resource
    RedissonClient redissonClient;

    @Test
    public void testRedisLock(){
        CountDownLatch countDownLatch = new CountDownLatch(5);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i=0;i<countDownLatch.getCount();i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await(); //每个线程在此等待
                        System.out.println("==================开始竞争锁资源================"+Thread.currentThread().getName());
                        RLock lock = redissonClient.getLock("lockKey");
                        boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
                        if (res) {
                            System.out.println("==================竞争锁资源成功================"+Thread.currentThread().getName());
                            Thread.sleep(1000);//模拟执行1S
                            lock.unlock();
                        } else {
                            System.out.println("==================竞争锁资源失败================"+Thread.currentThread().getName());
                        }
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
        }

        try {
            System.out.println("==================3秒后开始竞争锁资源==================");
            Thread.sleep(3000);
            latch.countDown(); // 触发所有线程全部开始执行
            countDownLatch.await(); // 等待线程全部执行完毕
            System.out.println("==================结束==================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
