package seek.starter.starter.mq.rabbitmq.starter.util;

import org.springframework.amqp.core.Queue;
import seek.starter.starter.support.config.object.mq.rabbitmq.BindingData;
import seek.starter.starter.support.config.object.mq.rabbitmq.ExchangeData;
import seek.starter.starter.support.config.object.mq.rabbitmq.QueueData;

import java.util.HashMap;
import java.util.Map;

public class QueueUtil {

    public static Queue getCustomizedQueue(QueueData queueData){
        Map<String, Object> args = new HashMap<>();
        if (queueData.isToQuorum())putQuorumArg(args);
        if (queueData.getDeadLetterExchange()!=null)putDeadLetterExchange(args,queueData.getDeadLetterExchange(),queueData.getDeadLetterRoutingKey());
        if (queueData.getDelayMillis()!=null)putTTL(args,queueData.getDelayMillis());
        return new Queue(queueData.getQueueName(), queueData.isDurable(), queueData.isExclusive(), queueData.isAutoDelete(), args);
    }

    public static void putQuorumArg(Map<String,Object> map){
        map.put("x-queue-type", "quorum");
    }
    public static void putTTL(Map<String,Object> map,long delayMillis){
        map.put("x-message-ttl", delayMillis);
    }
    public static void putDeadLetterExchange(Map<String,Object> map, BindingData bindingData){
        map.put("x-dead-letter-exchange", bindingData.getExchange().getExchangeName());
        map.put("x-dead-letter-routing-key", bindingData.getRoutingKey());
    }
    public static void putDeadLetterExchange(Map<String,Object> map, ExchangeData exchange, String deadLetterRoutingKey){
        map.put("x-dead-letter-exchange", exchange.getExchangeName());
        map.put("x-dead-letter-routing-key", deadLetterRoutingKey);
    }
}
