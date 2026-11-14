package seek.starter.starter.db.redis.starter.core;

import seek.starter.starter.support.config.object.db.redis.RedisKeyData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class RedisIdTemplate {
    private static final Random random=new Random();
    private final RedisTemplate redisTemplate;
    @Autowired
    public RedisIdTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long IdGenerateByIncreaseRandom(RedisKeyData key, int randomNumberMax) {
        return redisTemplate.increase(key,null,random.nextInt(randomNumberMax));
    }
    public long IdGenerateByIncrease(RedisKeyData key) {
        return redisTemplate.increase(key,null,1);
    }
}














