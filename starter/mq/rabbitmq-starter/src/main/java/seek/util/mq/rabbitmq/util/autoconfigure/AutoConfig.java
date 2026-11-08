package seek.util.mq.rabbitmq.util.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.util.mq.rabbitmq.util.configuration.ConverterConfig;
import seek.util.mq.rabbitmq.util.configuration.ErrorExchangeConfig;
import seek.util.mq.rabbitmq.util.configuration.MessageReturnCallBackConfig;
import seek.util.mq.rabbitmq.util.configuration.PropertiesConfig;
import seek.util.mq.rabbitmq.util.util.CorrleationUtil;
import seek.util.mq.rabbitmq.util.util.MQUtil;

@AutoConfiguration(value = "seek.util.mq.rabbitmq.util.AutoConfig")
@EnableConfigurationProperties(PropertiesConfig.class)
@Import({ConverterConfig.class, ErrorExchangeConfig.class, MessageReturnCallBackConfig.class})
public class AutoConfig {

    @Bean
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(
                CorrleationUtil.class, MQUtil.class
        );
    }
}
