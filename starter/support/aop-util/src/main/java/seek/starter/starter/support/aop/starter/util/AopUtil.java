package seek.starter.starter.support.aop.starter.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;

public class AopUtil {

    public static Method getMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return AopUtils.getMostSpecificMethod(signature.getMethod(), pjp.getTarget().getClass());
    }
}
