package seek.starter.starter.common.common.starter.core;

import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;
import seek.starter.starter.support.config.object.common.common.object.function.RunWithReturnParamFunction;

import java.util.concurrent.ConcurrentHashMap;

public class SecureHashGet<O,K,V> {

    private ConcurrentHashMap<K,V> map = new ConcurrentHashMap<>();
    private RunWithReturnParamFunction<V,O> getValue;
    private RunWithReturnParamFunction<K,O> getKey;

    private SecureHashGet() {}

    public SecureHashGet(RunWithReturnParamFunction<V,O> getValue,RunWithReturnParamFunction<K,O> getKey) {
        this.getValue=getValue;
        this.getKey=getKey;
    }

    //获取，并且如果没有就存放
    public V get(O originObject) {
        K key = getKey.run(originObject);
        //尝试获取值
        V result = map.get(key);
        //无值则存放值
        if (result == null){
            if (!put(originObject,key))throw new BizException(ErrorCodeEnum.DATA_SURVIVE);
        }
        else return result;
        return map.get(key);
    }

    //存放
    private boolean put(O originObject,K key) {
        V value = getValue.run(originObject);
        if (value == null) throw new BizException(ErrorCodeEnum.DATA_IS_EMPTY);
        return map.putIfAbsent(key, getValue.run(originObject)) == null;
    }
}
