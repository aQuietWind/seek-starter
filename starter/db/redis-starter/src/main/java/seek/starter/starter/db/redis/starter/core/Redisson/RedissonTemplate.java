package seek.starter.starter.db.redis.starter.core.Redisson;

import seek.util.common.config.object.db.redis.RedisKeyData;
import seek.util.common.config.object.common.common.object.exception.BizException;
import seek.util.common.config.object.common.common.object.exception.ErrorCodeEnum;
import seek.util.common.config.object.common.common.object.function.RunFunction;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedissonTemplate {

    private final RedissonClient redissonClient;
    public RedissonTemplate(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void lock(RedisKeyData key, Object id, RunFunction function) {
        RLock lock = redissonClient.getLock(key.getRedisKey(id));
        if (lock.tryLock()){
            try {
                function.run();
                return;
            }finally {
                lock.unlock();
            }
        }
        throw new BizException(ErrorCodeEnum.REQUEST_CONFLICT);
    }
}
