package seek.starter.starter.db.redis.redisson.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import seek.starter.starter.db.redis.redisson.starter.configure.PropertiesConfig;
import seek.starter.starter.db.redis.redisson.starter.configure.RedissonConfig;
import seek.starter.starter.db.redis.redisson.starter.enums.PackageEnum;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@Import({RedissonConfig.class})
@EnableConfigurationProperties({PropertiesConfig.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName();
    }
}
