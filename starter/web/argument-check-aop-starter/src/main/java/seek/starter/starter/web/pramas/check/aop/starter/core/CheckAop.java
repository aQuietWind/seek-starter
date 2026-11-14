package seek.starter.starter.web.pramas.check.aop.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckAop {

    @Around("execution(* tlias.tlias.Service.impl.*.*(..))")         //设置所要测试的类的范围为Service实现类的所有方法
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();      //原始方法的运行结果
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(pjp.getSignature()+"共耗时为"+time+"ms");    //获取方法名称，并打印其耗时
        return result;
    }
}
