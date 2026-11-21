package seek.starter.starter.common.argument.check.common.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.common.argument.check.common.starter.core.CheckAop;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.support.argument.check.aop.starter.enums.PackageEnum;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(CheckAop.class);
    }
}
