package seek.starter.starter.common.event.respond.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.common.event.respond.starter.config.PropertiesConfig;
import seek.starter.starter.common.event.respond.starter.enums.PackageEnum;
import seek.starter.starter.common.event.respond.starter.support.CheckPool;
import seek.starter.starter.common.event.respond.starter.support.DelayPool;
import seek.starter.starter.common.event.respond.starter.support.ExecutePool;
import seek.starter.starter.common.event.respond.starter.core.ValueFactory;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties({PropertiesConfig.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(CheckPool.class,DelayPool.class,ExecutePool.class,ValueFactory.class);
    }
}
