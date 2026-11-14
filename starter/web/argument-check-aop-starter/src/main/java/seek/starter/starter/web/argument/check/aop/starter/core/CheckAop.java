package seek.starter.starter.web.argument.check.aop.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestParam;
import seek.starter.starter.web.argument.check.aop.starter.enums.PackageEnum;
import seek.starter.starter.web.argument.check.aop.starter.support.CheckMark;

import java.lang.reflect.Method;

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

    //设置所要测试的类的范围

    @Around("checkPointCut()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        // 遍历每个参数
        for (int i = 0; i < method.getParameterCount(); i++) {
            MethodParameter mp = new MethodParameter(method, i);
            System.out.println(mp);
            CheckMark checkMark = mp.getParameterAnnotation(CheckMark.class);
            if (checkMark != null) {
                System.err.println(checkMark.value());
            }
        }

        long start = System.currentTimeMillis();
        Object result = pjp.proceed();      //原始方法的运行结果
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(pjp.getSignature()+"共耗时为"+time+"ms");    //获取方法名称，并打印其耗时
        return result;
    }
}
