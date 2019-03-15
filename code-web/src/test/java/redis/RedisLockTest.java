package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class RedisLockTest {
    @Resource
    RedissonClient redissonClient;

    @Test
    public void testRedisLock(){

        testReentrantLock();
        testReentrantLock2();
        Thread thread = new Thread(new Thread1());
        thread.start();


    }


    public void testReentrantLock(){
        RLock lock = redissonClient.getLock("anyLock");
        try{
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(3, 100, TimeUnit.SECONDS);
            if(res){ //成功
                // do your business
                dealBusiness(lock.getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //lock.unlock();
        }
    }

    public void testReentrantLock2(){
        RLock lock = redissonClient.getLock("anyLock");
        try{
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            lock.lock(100, TimeUnit.SECONDS);

            dealBusiness(lock.getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //lock.unlock();
        }
    }

    class Thread1 implements Runnable{

        @Override
        public void run() {
            try {
                dealBusiness("thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void dealBusiness(String name) throws InterruptedException {
        System.out.println("==================lock name================"+name);
    }


}
