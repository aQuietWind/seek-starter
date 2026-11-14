package seek.starter.starter.web.argument.check.aop.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import seek.starter.starter.common.common.starter.core.SecureHashGet;
import seek.starter.starter.support.aop.starter.util.AopUtil;
import seek.starter.starter.web.argument.check.aop.starter.enums.CheckLevel;
import seek.starter.starter.web.argument.check.aop.starter.enums.PackageEnum;
import seek.starter.starter.web.argument.check.aop.starter.support.CheckEntry;
import seek.starter.starter.web.argument.check.aop.starter.support.CheckMark;
import seek.starter.starter.web.argument.check.aop.starter.support.CheckStore;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Aspect
public class ControllerCheckAop {

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

    private final CheckStore checkStore;
    @Autowired
    public ControllerCheckAop(CheckStore checkStore) {
        this.checkStore = checkStore;
    }

    //存储每个方法各自参数的信息
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
        String[] parameterCheckNames = parameterHash.get(AopUtil.getMethod(pjp));
        Object[] args = pjp.getArgs();
        // 遍历每个参数
        for (int i = 0; i < parameterCheckNames.length; i++) {
            String checkName=parameterCheckNames[i];
            if (checkName==null)continue;
            CheckEntry<?> checkEntry=checkStore.get(checkName);
            if(checkEntry==null)continue;
            check(checkEntry,args[i]);
        }
        return pjp.proceed();      //原始方法的运行结果
    }

    //校验参数
    private <T> void check(CheckEntry<T> checkEntry,Object arg){
        T realArg=checkEntry.getParameterClazz().cast(arg);
        checkEntry.check(realArg);
    }

}
