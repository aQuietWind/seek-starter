package seek.starter.starter.db.redis.starter.core;


import lombok.AllArgsConstructor;
import seek.starter.starter.common.common.starter.core.LimitStore;
import seek.starter.starter.common.common.starter.support.Container;
import seek.starter.starter.support.config.object.db.redis.RedisKeyData;

import java.util.Map;

@AllArgsConstructor
public class LimitHashStore<T> {
    private final RedisHashTemplate redisHashTemplate;
    private final RedisKeyData storeKeyData;
    private final Object id;
    private final Integer maxSize;
    private final Class<T> storeClass;

    public LimitStore<T> getStore() {
        Map<Object,Object> result=redisHashTemplate.getAll(storeKeyData, id);
        LimitStore<T> store=new LimitStore<>(maxSize);
        for(Map.Entry<Object,Object> entry:result.entrySet()){
            store.putOne(new Container<>(entry.getKey(),entry.getValue()));
        }
    }
}
