package seek.starter.starter.db.redis.aop.help.starter.core;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import seek.starter.starter.common.common.starter.core.SecureHashGet;
import seek.starter.starter.db.redis.aop.help.starter.enums.PackageEnum;
import seek.starter.starter.db.redis.starter.core.RedisTemplate;
import seek.starter.starter.support.aop.starter.util.AopUtil;
import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;
import seek.starter.starter.support.config.object.db.redis.RedisKeyData;

import java.lang.reflect.Method;

@Aspect
public class CooldownAop {

    @Pointcut("@annotation("+ PackageEnum.Full_Package_Prefix+"core.CooldownMark)")
    public void cooldownPointCut() {}

    private final RedisTemplate redisTemplate;
    private final Binder binder;
    @Autowired
    public CooldownAop(RedisTemplate redisTemplate, Environment environment) {
        this.redisTemplate = redisTemplate;
        this.binder = Binder.get(environment);
    }

    //存储每个方法各自参数的信息
    private SecureHashGet<Method, Method, CooldownRedisKeyData> parameterHash;

    @PostConstruct
    public void init() {
        //初始化参数存储集
        parameterHash=new SecureHashGet<>(
                method->{
                    //获取注解信息
                    String prefixPath = method.getAnnotation(CooldownMark.class).propertiesDestination();
                    //Spring自动装填
                    RedisKeyData redisKeyData = binder.bind(prefixPath, Bindable.of(RedisKeyData.class))
                            .orElseThrow(() -> new BizException(ErrorCodeEnum.DATA_NOT_FOUND,"冷却配置" + prefixPath+"不存在"));
                    //遍历每个参数,寻找绑定的参数
                    for (int i = 0; i < method.getParameterCount(); i++) {
                        MethodParameter mp = new MethodParameter(method, i);
                        //获取checkName,方便后续对应check方法
                        IdMark idMark = mp.getParameterAnnotation(IdMark.class);
                        if (idMark != null) return new CooldownRedisKeyData(i,redisKeyData);
                    }
                    return new CooldownRedisKeyData(0,redisKeyData);
                }, method->method
        );
    }

    //设置所要测试的方法的范围
    @Around("cooldownPointCut()")
    public Object checkArguments(ProceedingJoinPoint pjp) throws Throwable{
        //获取key
        CooldownRedisKeyData key = parameterHash.get(AopUtil.getMethod(pjp));
        //检查冷却
        cooldown(key, pjp.getArgs());
        // 遍历每个参数
        return pjp.proceed();      //原始方法的运行结果
    }

    //校验参数
    private void cooldown(CooldownRedisKeyData key,Object[] args){
        if (key.index()>=args.length)throw new BizException(ErrorCodeEnum.ERROR_USE,"无可使用参数进行冷却校验");
        redisTemplate.checkCooldown(key.redisKeyData(),args[key.index()]);
    }


    //存储冷却信息
    public record CooldownRedisKeyData(Integer index, RedisKeyData redisKeyData) {}

}
