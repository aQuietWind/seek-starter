package seek.starter.starter.support.argument.check.aop.starter.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.MethodParameter;
import seek.starter.starter.common.common.starter.core.SecureHashGet;
import seek.starter.starter.support.aop.starter.util.AopUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class NormalCheckAop{


    private final CheckStore checkStore;
    public NormalCheckAop(CheckStore checkStore) {
        this.checkStore = checkStore;
    }

    //存储每个方法各自参数的信息
    private final SecureHashGet<Method, Method, String[]> parameterHash=new SecureHashGet<>(
            method->{
                ArrayList<String> parameterCheckNames=new ArrayList<>();
                // 遍历每个参数
                for (int i = 0; i < method.getParameterCount(); i++) {
                    //获取checkName,方便后续对应check方法
                    parameterCheckNames.add(getCheckName(new MethodParameter(method, i)));
                }
                return parameterCheckNames.toArray(new String[0]);
            }, method->method
    );

    //设置所要测试的类的范围
    public Object checkArguments(ProceedingJoinPoint pjp) throws Throwable{
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

    public abstract String getCheckName(MethodParameter mp);

    public abstract Object aopCheck(ProceedingJoinPoint pjp) throws Throwable;
}
