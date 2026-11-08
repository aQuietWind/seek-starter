package seek.starter.starter.mq.rocketmq.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.mq.rocketmq.starter.enums.PackageEnum;
import seek.starter.starter.mq.rocketmq.starter.support.BizStore;
import seek.starter.starter.mq.rocketmq.starter.support.DefaultTransactionListener;
import seek.starter.starter.mq.rocketmq.starter.core.ProducerTemplate;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(ProducerTemplate.class, BizStore.class,DefaultTransactionListener.class);
    }
}
