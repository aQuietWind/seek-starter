package seek.starter.starter.web.web.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.web.web.starter.config.PropertiesConfig;
import seek.starter.starter.web.web.starter.support.GlobalRequestExceptionHandler;
import seek.starter.starter.web.web.starter.support.TokenInterceptor;
import seek.starter.starter.web.web.starter.support.WebConfigImpl;
import seek.starter.starter.web.web.starter.enums.PackageEnum;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties(PropertiesConfig.class)
@Import({WebConfigImpl.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(TokenInterceptor.class, GlobalRequestExceptionHandler.class,WebConfigImpl.class);
    }
}
