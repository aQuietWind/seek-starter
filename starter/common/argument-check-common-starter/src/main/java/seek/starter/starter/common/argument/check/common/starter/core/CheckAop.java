package seek.starter.starter.common.argument.check.common.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import seek.starter.starter.support.argument.check.aop.starter.core.CheckMark;
import seek.starter.starter.support.argument.check.aop.starter.core.CheckStore;
import seek.starter.starter.support.argument.check.aop.starter.core.NormalCheckAop;
import seek.starter.starter.support.argument.check.aop.starter.enums.PackageEnum;

@Aspect
public class CheckAop extends NormalCheckAop {

    @Pointcut(
            "@within("+ PackageEnum.CheckMark+")"
            + "&& !@annotation("+PackageEnum.SkipCheckMark+")"
    )
    public void checkPointCut() {}

    @Autowired
    public CheckAop(CheckStore checkStore) {
        super(checkStore);
    }

    @Override
    public String getCheckName(MethodParameter mp) {
        //获取checkName,方便后续对应check方法
        CheckMark checkMark = mp.getParameterAnnotation(CheckMark.class);
        if (checkMark != null) return checkMark.value();
        return null;
    }

    @Override
    @Around("checkPointCut")
    public Object aopCheck(ProceedingJoinPoint pjp) throws Throwable {
        return checkArguments(pjp);
    }

}
