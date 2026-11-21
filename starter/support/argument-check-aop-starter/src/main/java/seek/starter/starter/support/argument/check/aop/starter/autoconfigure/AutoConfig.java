package seek.starter.starter.support.argument.check.aop.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.support.argument.check.aop.starter.enums.PackageEnum;
import seek.starter.starter.support.argument.check.aop.starter.core.CheckStore;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(CheckStore.class);
    }
}
