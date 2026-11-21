package seek.starter.starter.mq.rabbitmq.starter.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.support.config.object.mq.rabbitmq.DestinationData;

import java.util.UUID;

@Slf4j
public class MQTemplate {

    private final RabbitTemplate rabbitTemplate;
    private final GlobalCorrelationTemplate globalCorrelationTemplate;

    @Autowired
    public MQTemplate(RabbitTemplate rabbitTemplate, GlobalCorrelationTemplate globalCorrelationTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.globalCorrelationTemplate = globalCorrelationTemplate;
    }



    //发送消息
    public void send(DestinationData destination, Object message){
        rabbitTemplate.convertAndSend(destination.getExchangeName(),destination.getRoutingKey(),message,
                globalCorrelationTemplate.getCorrelation(destination));
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
                , globalCorrelationTemplate.getCorrelationWithId(destination,letterId));
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
                , globalCorrelationTemplate.getCorrelation(destination));
    }

    //分钟转毫秒
    public String minuteToMillis(int minute){
        return ""+minute*60*1000;
    }

}
