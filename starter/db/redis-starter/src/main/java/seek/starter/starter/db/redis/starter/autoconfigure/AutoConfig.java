package seek.starter.starter.db.redis.starter.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import seek.starter.starter.db.redis.starter.config.PropertiesConfig;
import seek.starter.starter.db.redis.starter.util.Redis.RedisBitMapUtil;
import seek.starter.starter.db.redis.starter.util.Redis.RedisIdUtil;
import seek.starter.starter.db.redis.starter.util.Redis.RedisStreamUtil;
import seek.starter.starter.db.redis.starter.util.Redis.RedisUtil;

@Configuration
public class AutoConfig {
    @Bean
    public PropertiesConfig propertiesConfig(){
        return new PropertiesConfig();
    }

    @Bean
    public RedisBitMapUtil redisBitMapUtil(StringRedisTemplate stringRedisTemplate) {
        return new RedisBitMapUtil(stringRedisTemplate);
    }
    @Bean
    public RedisUtil redisUtil(StringRedisTemplate stringRedisTemplate) {
        return new RedisUtil(stringRedisTemplate);
    }
    @Bean
    public RedisStreamUtil redisStreamUtil(StringRedisTemplate stringRedisTemplate) {
        return new RedisStreamUtil(stringRedisTemplate);
    }
    @Bean
    public RedisIdUtil redisIdUtil(RedisUtil redisUtil) {
        return new RedisIdUtil(redisUtil);
    }
}
