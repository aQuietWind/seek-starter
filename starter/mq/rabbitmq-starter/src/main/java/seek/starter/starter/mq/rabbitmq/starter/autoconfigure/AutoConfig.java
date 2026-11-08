package seek.starter.starter.mq.rabbitmq.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import seek.starter.starter.mq.rabbitmq.starter.enums.PackageEnum;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.mq.rabbitmq.starter.configuration.ConverterConfig;
import seek.starter.starter.mq.rabbitmq.starter.configuration.ErrorExchangeConfig;
import seek.starter.starter.mq.rabbitmq.starter.configuration.MessageReturnCallBackConfig;
import seek.starter.starter.mq.rabbitmq.starter.configuration.PropertiesConfig;
import seek.starter.starter.mq.rabbitmq.starter.core.CorrleationTemplate;
import seek.starter.starter.mq.rabbitmq.starter.core.MQTemplate;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties(PropertiesConfig.class)
@Import({ConverterConfig.class, ErrorExchangeConfig.class, MessageReturnCallBackConfig.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(CorrleationTemplate.class, MQTemplate.class);
    }
}
