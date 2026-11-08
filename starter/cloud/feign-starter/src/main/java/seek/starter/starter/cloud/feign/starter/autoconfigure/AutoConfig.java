package seek.starter.starter.cloud.feign.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.cloud.feign.starter.configure.PropertiesConfig;
import seek.starter.starter.cloud.feign.starter.enums.PackageEnum;
import seek.starter.starter.cloud.feign.starter.support.FeignInterceptor;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties({PropertiesConfig.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(FeignInterceptor.class);
    }
}
