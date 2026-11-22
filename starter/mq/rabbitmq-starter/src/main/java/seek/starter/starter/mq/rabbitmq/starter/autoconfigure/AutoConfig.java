package seek.starter.starter.mq.rabbitmq.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import seek.starter.starter.mq.rabbitmq.starter.enums.PackageEnum;
import seek.starter.starter.mq.rabbitmq.starter.util.BindingRegisterBeanUtil;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.mq.rabbitmq.starter.configuration.ConverterConfig;
import seek.starter.starter.mq.rabbitmq.starter.configuration.ErrorExchangeDefaultBindingConfig;
import seek.starter.starter.mq.rabbitmq.starter.configuration.MessageReturnCallBackConfig;
import seek.starter.starter.mq.rabbitmq.starter.configuration.PropertiesConfig;
import seek.starter.starter.mq.rabbitmq.starter.core.GlobalCorrelationTemplate;
import seek.starter.starter.mq.rabbitmq.starter.core.MQTemplate;

import java.util.List;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties(PropertiesConfig.class)
@Import({ConverterConfig.class, ErrorExchangeDefaultBindingConfig.class, MessageReturnCallBackConfig.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName( MQTemplate.class);
    }

    @Bean(PackageEnum.Full_Package_Prefix +"core.GlobalCorrelationTemplate")
    @ConditionalOnMissingBean
    public static GlobalCorrelationTemplate globalCorrelationTemplate() {
        return new GlobalCorrelationTemplate();
    }


    //创建延迟交换机组
    @Bean
    public BeanDefinitionRegistryPostProcessor delayExchangeBeanDefinitionRegistryPostProcessor(PropertiesConfig propertiesConfig) {
        return BindingRegisterBeanUtil.quickRegistrar(
                PackageEnum.appendByFullPackage("DelayExchangeAndQueues")
                , List.of(propertiesConfig.getDelay().getDelayExchange())
                , List.of(
                        propertiesConfig.getDelay().getRedeliver()
                        ,propertiesConfig.getDelay().getLevelOne()
                        ,propertiesConfig.getDelay().getLevelTwo()
                        ,propertiesConfig.getDelay().getLevelThree()
                        ,propertiesConfig.getDelay().getLevelFour()
                        ,propertiesConfig.getDelay().getLevelFive()
                        ,propertiesConfig.getDelay().getLevelSix()
                        ,propertiesConfig.getDelay().getLevelSeven()
                        ,propertiesConfig.getDelay().getLevelEight()
                        ,propertiesConfig.getDelay().getLevelNine()
                        ,propertiesConfig.getDelay().getLevelTen()
                ));
    }
}
