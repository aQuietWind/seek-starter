package seek.starter.starter.db.redis.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import seek.starter.starter.db.redis.starter.config.PropertiesConfig;
import seek.starter.starter.db.redis.starter.core.Redis.*;
import seek.starter.starter.db.redis.starter.enums.PackageEnum;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(RedisBitMapTemplate.class,RedisIdTemplate.class,RedisStreamTemplate.class,
                RedisScriptTemplate.class,RedisTemplate.class);
    }
}
