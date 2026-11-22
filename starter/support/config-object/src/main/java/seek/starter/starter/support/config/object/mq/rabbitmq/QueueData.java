package seek.starter.starter.support.config.object.mq.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueData {
    @NonNull
    private String queueName;
    private boolean toQuorum=true;
    private boolean durable=true;
    private boolean exclusive=false;
    private boolean autoDelete=false;
    private Long delayMillis=null;
    private ExchangeData deadLetterExchange=null;
    private String deadLetterRoutingKey=null;

    public QueueData(@NonNull String queueName) {
        this.queueName = queueName;
    }
    public QueueData(@NonNull String queueName, long delayMillis) {
        this.queueName = queueName;
        this.delayMillis = delayMillis;
    }
    public QueueData(@NonNull String queueName, ExchangeData deadLetterExchange, String deadLetterRoutingKey) {
        this.queueName = queueName;
        this.deadLetterExchange = deadLetterExchange;
        this.deadLetterRoutingKey = deadLetterRoutingKey;
    }
    public QueueData(@NonNull String queueName, long delayMillis, ExchangeData deadLetterExchange, String deadLetterRoutingKey) {
        this.queueName = queueName;
        this.delayMillis = delayMillis;
        this.deadLetterExchange = deadLetterExchange;
        this.deadLetterRoutingKey = deadLetterRoutingKey;
    }
    public QueueData(@NonNull String queueName, long delayMillis, BindingData bindingData) {
        this.queueName = queueName;
        this.delayMillis = delayMillis;
        this.deadLetterExchange = bindingData.getExchange();
        this.deadLetterRoutingKey = bindingData.getRoutingKey();
    }
}
