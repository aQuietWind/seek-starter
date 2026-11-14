package seek.starter.starter.web.argument.check.aop.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import seek.starter.starter.web.argument.check.aop.starter.enums.PackageEnum;

@Aspect
public class CheckAop {

    @Around(
            "@within("+ PackageEnum.Full_Package_Prefix +"support.AbleCheckPointCut)"
//                    + """
//                    && (@annotation(org.springframework.web.bind.annotation.GetMapping)
//                    || @annotation(org.springframework.web.bind.annotation.PostMapping)
//                    || @annotation(org.springframework.web.bind.annotation.PutMapping)
//                    || @annotation(org.springframework.web.bind.annotation.DeleteMapping)
//                    || @annotation(org.springframework.web.bind.annotation.RequestMapping) )"""
            + "&& !@annotation("+PackageEnum.Full_Package_Prefix+"support.SkipCheckPointCut)"
    )
    //设置所要测试的类的范围
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();      //原始方法的运行结果
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(pjp.getSignature()+"共耗时为"+time+"ms");    //获取方法名称，并打印其耗时
        return result;
    }
}
