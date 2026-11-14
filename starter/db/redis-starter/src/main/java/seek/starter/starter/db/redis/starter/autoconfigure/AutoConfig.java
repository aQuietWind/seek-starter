package seek.starter.starter.db.redis.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.db.redis.starter.configure.PropertiesConfig;
import seek.starter.starter.db.redis.starter.core.*;
import seek.starter.starter.db.redis.starter.enums.PackageEnum;
import seek.starter.starter.db.redis.starter.util.RedisScriptUtil;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties(PropertiesConfig.class)
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(RedisBitMapTemplate.class, RedisIdTemplate.class, RedisStreamTemplate.class
                ,RedisTemplate.class);
    }
}
