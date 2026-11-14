package seek.starter.starter.mq.rabbitmq.starter.configuration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorExchangeDefaultBindingConfig {
    private final RabbitTemplate rabbitTemplate;
    private final PropertiesConfig propertiesConfig;

    @Autowired
    public ErrorExchangeDefaultBindingConfig(RabbitTemplate rabbitTemplate, PropertiesConfig propertiesConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.propertiesConfig = propertiesConfig;
    }
    @Bean
    public MessageRecoverer recoverer(){
        //声明目标交换机以及key，返回该处理器
        return new RepublishMessageRecoverer(rabbitTemplate, propertiesConfig.getErrorExchange().getExchangeName());
    }
}