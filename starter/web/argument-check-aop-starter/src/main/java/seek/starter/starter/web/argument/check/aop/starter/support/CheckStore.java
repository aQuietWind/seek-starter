package seek.starter.starter.web.argument.check.aop.starter.support;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;
import seek.starter.starter.web.argument.check.aop.starter.enums.CheckLevel;

import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor
public class CheckStore {

    private ConcurrentHashMap<String,CheckEntry<?>> map=new ConcurrentHashMap<>();

    public CheckEntry<?> get(String key){
        return map.get(key);
    }

    //添加参数校验元素于Store
    public synchronized boolean putIfAbsent(@NonNull CheckEntry<?> entry){
        if (map.containsKey(entry.getCheckName())){
            //如果优先级更高或者等同,则覆盖
            if (CheckLevel.priorOrEqual(entry.getLevel(),this.get(entry.getCheckName()).getLevel()))map.put(entry.getCheckName(),entry);
            else return false;
        }else {
            map.put(entry.getCheckName(),entry);
        }
        return true;
    }

    public boolean[] putIfAbsent(CheckEntry<?> ... entries){
        if (entries==null)throw new BizException(ErrorCodeEnum.DATA_IS_EMPTY);
        boolean[] result=new boolean[entries.length];
        for (int i = 0; i < entries.length; i++) {
            result[i]=putIfAbsent(entries[i]);
        }
        return result;
    }
}
