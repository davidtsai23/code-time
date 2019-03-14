package redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisLock {

    @Resource
    RedissonClient redissonClient;

    public RLock getFairLock(String lockName){
        return redissonClient.getFairLock(lockName);
    }
}
