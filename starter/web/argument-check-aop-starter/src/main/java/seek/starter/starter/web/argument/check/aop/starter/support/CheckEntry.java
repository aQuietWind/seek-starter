package seek.starter.starter.web.argument.check.aop.starter.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;
import seek.starter.starter.support.config.object.common.common.object.function.RunWithReturnParamFunction;
import seek.starter.starter.web.argument.check.aop.starter.enums.CheckLevel;

@Data
@AllArgsConstructor
public class CheckEntry<T> {
    @NonNull
    private CheckLevel level;
    @NonNull
    private String checkName;
    @NonNull
    private Class<T> parameterClazz;
    @NonNull
    private RunWithReturnParamFunction<Boolean,T> check;

    //防止正常情况下出现无参对象,当然,硬要反射那没办法
    private CheckEntry(){}

    //校验参数
    public void check(T arg){
        if (!check.run(arg))throw new BizException(ErrorCodeEnum.PARAM_ERROR);
    }
}
