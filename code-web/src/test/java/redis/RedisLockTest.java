package redis;

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

    public void testRedisLock(){
        RLock rLock = redisLock.getFairLock("lock");

    }
}
