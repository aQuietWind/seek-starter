package seek.starter.starter.common.common.starter.util.exception;

import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BizThrowUtil {

    public static void throwBizError(ErrorCodeEnum errorCodeEnum){
        throw new BizException(errorCodeEnum);
    }
}
