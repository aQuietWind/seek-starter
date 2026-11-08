package seek.starter.starter.mq.rabbitmq.starter.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import seek.util.common.config.object.mq.rabbitmq.DestinationData;

import java.util.UUID;

@Slf4j
public class MQTemplate {

    private final RabbitTemplate rabbitTemplate;
    private final CorrleationTemplate corrleationTemplate;

    @Autowired
    public MQTemplate(RabbitTemplate rabbitTemplate, CorrleationTemplate corrleationTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.corrleationTemplate = corrleationTemplate;
    }



    //发送消息
    public void send(DestinationData destination, Object message){
        rabbitTemplate.convertAndSend(destination.getExchangeName(),destination.getRoutingKey(),message, corrleationTemplate.getCorrelation(destination.getExchangeName(), destination.getRoutingKey()));
    }


    //发送带过期时间的消息并且返回letterId使用
    public String sendWithTLLAndGetId(DestinationData destination, Object message,String tllMilliSeconds){
        String letterId=UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(destination.getExchangeName(),destination.getRoutingKey()
                ,message
                , msg->{
            //设置ttl
            msg.getMessageProperties().setExpiration(tllMilliSeconds);
            msg.getMessageProperties().setMessageId(letterId);
            return msg;}
                , corrleationTemplate.getCorrelationWithId(destination.getExchangeName(), destination.getRoutingKey(),letterId));
        return letterId;
    }

    //发送带过期时间的消息
    public void sendWithTLL(DestinationData destination, Object message ,String tllMilliSeconds){
        rabbitTemplate.convertAndSend(destination.getExchangeName(),destination.getRoutingKey()
                ,message
                , msg->{
            //设置ttl
            msg.getMessageProperties().setExpiration(tllMilliSeconds);
            return msg;}
                , corrleationTemplate.getCorrelation(destination.getExchangeName(), destination.getRoutingKey()));
    }

    //分钟转毫秒
    public String minuteToMillis(int minute){
        return ""+minute*60*1000;
    }

}
