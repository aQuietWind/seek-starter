package seek.starter.starter.web.argument.check.aop.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.web.argument.check.aop.starter.core.ControllerCheckAop;
import seek.starter.starter.web.argument.check.aop.starter.enums.PackageEnum;
import seek.starter.starter.web.argument.check.aop.starter.support.CheckStore;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
//@EnableConfigurationProperties(PropertiesConfig.class)
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(ControllerCheckAop.class, CheckStore.class);
    }
}
