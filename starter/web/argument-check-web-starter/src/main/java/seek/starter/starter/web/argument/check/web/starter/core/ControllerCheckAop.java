package seek.starter.starter.web.argument.check.web.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestParam;
import seek.starter.starter.support.argument.check.aop.starter.core.CheckMark;
import seek.starter.starter.support.argument.check.aop.starter.core.CheckStore;
import seek.starter.starter.support.argument.check.aop.starter.core.NormalCheckAop;
import seek.starter.starter.support.argument.check.aop.starter.enums.PackageEnum;

@Aspect
public class ControllerCheckAop extends NormalCheckAop {

    @Pointcut(
            "@within("+ PackageEnum.CheckMark+")"
            + "&&("
            +
                    "@annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.RequestMapping) "
            + ")"
            + "&& !@annotation("+PackageEnum.SkipCheckMark+")"
    )
    public void checkPointCut() {}

    @Autowired
    public ControllerCheckAop(CheckStore checkStore) {
        super(checkStore);
    }

    @Override
    public String getCheckName(MethodParameter mp) {
        //获取checkName,方便后续对应check方法
        CheckMark checkMark = mp.getParameterAnnotation(CheckMark.class);
        if (checkMark != null) return checkMark.value();
        RequestParam requestParam=mp.getParameterAnnotation(RequestParam.class);
        if (requestParam != null) return requestParam.value();
        return null;
    }

    @Override
    @Around("checkPointCut()")
    public Object aopCheck(ProceedingJoinPoint pjp) throws Throwable {
        return checkArguments(pjp);
    }

}
