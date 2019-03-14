package redis;

import com.jfs.redis.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class RedisLockTest {
    @Resource
    RedisLock redisLock;

    @Test
    public void testRedisLock(){
        Thread thread = new Thread(new Thread1());
        thread.start();

        Thread thread2 = new Thread(new Thread2());
        thread2.start();

    }

    class Thread1 implements Runnable{

        @Override
        public void run() {
            while (true) {
                RLock rLock = null;
                try {
                    rLock = redisLock.getFairLock("1");
                    rLock.lock();
                    System.out.println("1");
                }finally {
                    if (rLock != null){

                        rLock.unlock();
                    }
                }
            }
        }
    }

    class Thread2 implements Runnable{

        @Override
        public void run() {
            while (true) {
                RLock rLock = null;
                try {
                    rLock = redisLock.getFairLock("1");
                    rLock.lock();
                    System.out.println("2");
                }finally {
                    if (rLock != null){

                        rLock.unlock();
                    }
                }
            }
        }
    }


}
