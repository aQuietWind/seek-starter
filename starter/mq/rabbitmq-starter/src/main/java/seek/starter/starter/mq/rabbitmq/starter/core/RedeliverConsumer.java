package seek.starter.starter.mq.rabbitmq.starter.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.mq.rabbitmq.starter.enums.DelayPropertiesEnum;
import seek.starter.starter.support.config.object.mq.rabbitmq.MessageData;

@Slf4j
public class RedeliverConsumer {

    private final MQTemplate mqTemplate;

    @Autowired
    public RedeliverConsumer(MQTemplate mqTemplate) {
        this.mqTemplate = mqTemplate;
    }

    @RabbitListener(queues = DelayPropertiesEnum.RedeliverPropertiesDestination)
    public <T> void redeliver(MessageData<T> messageData) {
        mqTemplate.send(messageData);
    }
}
