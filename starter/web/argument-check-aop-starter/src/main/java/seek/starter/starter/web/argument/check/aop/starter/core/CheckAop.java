package seek.starter.starter.web.argument.check.aop.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestParam;
import seek.starter.starter.common.common.starter.core.SecureHashGet;
import seek.starter.starter.web.argument.check.aop.starter.enums.PackageEnum;
import seek.starter.starter.web.argument.check.aop.starter.support.CheckMark;
import seek.starter.starter.web.argument.check.aop.starter.support.RequestParameter;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Aspect
public class CheckAop {

    @Pointcut(
            "@within("+ PackageEnum.Full_Package_Prefix +"support.AbleCheckPointCut)"
                    + """
                    && (@annotation(org.springframework.web.bind.annotation.GetMapping)
                    || @annotation(org.springframework.web.bind.annotation.PostMapping)
                    || @annotation(org.springframework.web.bind.annotation.PutMapping)
                    || @annotation(org.springframework.web.bind.annotation.DeleteMapping)
                    || @annotation(org.springframework.web.bind.annotation.RequestMapping) )"""
                    + "&& !@annotation("+PackageEnum.Full_Package_Prefix+"support.SkipCheckPointCut)")
    public void checkPointCut() {}

    private final SecureHashGet<Method, Method, String[]> parameterHash=new SecureHashGet<>(
            method->{
                ArrayList<String> parameterCheckNames=new ArrayList<>();
                // 遍历每个参数
                for (int i = 0; i < method.getParameterCount(); i++) {
                    MethodParameter mp = new MethodParameter(method, i);
                    //获取checkName,方便后续对应check方法
                    CheckMark checkMark = mp.getParameterAnnotation(CheckMark.class);
                    if (checkMark != null) {
                        parameterCheckNames.add(checkMark.value());
                        continue;
                    }
                    RequestParam requestParam=mp.getParameterAnnotation(RequestParam.class);
                    if (requestParam != null) {
                        parameterCheckNames.add(requestParam.value());
                        continue;
                    }
                    parameterCheckNames.add(null);
                }
                return parameterCheckNames.toArray(new String[0]);
            }, method->method
    );

    //设置所要测试的类的范围
    @Around("checkPointCut()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable{
        String[] parameterCheckNames = parameterHash.get(getMethod(pjp));
        // 遍历每个参数
        for (String requestParameter : parameterCheckNames) {
            System.err.println(requestParameter);
        }
        return pjp.proceed();      //原始方法的运行结果
    }

    private Method getMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return AopUtils.getMostSpecificMethod(signature.getMethod(), pjp.getTarget().getClass());
    }
}
