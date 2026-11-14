package seek.starter.starter.mq.rabbitmq.error.system.starter.core;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import seek.starter.starter.mq.rabbitmq.error.system.starter.configuration.PropertiesConfig;

@Component
public class ErrorConsumer {

    @RabbitListener(queues=PropertiesConfig.ErrorQueueDestination)
    public void listen(){}
}
