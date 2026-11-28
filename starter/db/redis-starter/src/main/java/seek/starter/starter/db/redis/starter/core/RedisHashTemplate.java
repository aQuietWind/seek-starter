package seek.starter.starter.db.redis.starter.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import seek.starter.starter.support.config.object.db.redis.RedisKeyData;

import java.util.List;
import java.util.Map;

public class RedisHashTemplate {
    private final StringRedisTemplate stringRedisTemplate;
    @Autowired
    public RedisHashTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public<T> T getOneColumn(RedisKeyData redisKeyData,Object id,String columnName) {
        return (T) stringRedisTemplate.opsForHash().get(redisKeyData.getRedisKey(id), columnName);
    }

    public List<Object> getColumn(RedisKeyData redisKeyData, Object id, String ... columnName) {
        return stringRedisTemplate.opsForHash().multiGet(redisKeyData.getRedisKey(id), List.of(columnName));
    }

    public Map<Object, Object> getAll(RedisKeyData redisKeyData, Object id) {
        return stringRedisTemplate.opsForHash().entries(redisKeyData.getRedisKey(id));
    }
}
