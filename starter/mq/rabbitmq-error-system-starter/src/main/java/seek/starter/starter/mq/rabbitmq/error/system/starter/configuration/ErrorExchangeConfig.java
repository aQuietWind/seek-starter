package seek.starter.starter.mq.rabbitmq.error.system.starter.configuration;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import seek.starter.starter.mq.rabbitmq.error.system.starter.enums.PackageEnum;
import seek.starter.starter.mq.rabbitmq.starter.util.BindingRegisterBeanUtil;
import seek.starter.starter.mq.rabbitmq.starter.util.QueueUtil;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ErrorExchangeConfig {
    private final RabbitTemplate rabbitTemplate;
    private final PropertiesConfig propertiesConfig;

    @Autowired
    public ErrorExchangeConfig(RabbitTemplate rabbitTemplate, PropertiesConfig propertiesConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.propertiesConfig = propertiesConfig;
    }

    //创建异常处理交换机
    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return BindingRegisterBeanUtil.quickRegistrar(
                PackageEnum.appendByFullPackage("ErrorExchangeBinding")
                , List.of(propertiesConfig.getErrorExchange())
                , List.of(propertiesConfig.getErrorBinding()));
    }

    @Bean
    public MessageRecoverer recoverer(){
        //声明目标交换机以及key，返回该处理器
        return new RepublishMessageRecoverer(rabbitTemplate, propertiesConfig.getErrorExchange().getExchangeName());
    }
}