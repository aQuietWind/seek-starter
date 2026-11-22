package seek.starter.starter.mq.rabbitmq.starter.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.mq.rabbitmq.starter.configuration.PropertiesConfig;
import seek.starter.starter.support.config.object.mq.rabbitmq.DestinationData;
import seek.starter.starter.support.config.object.mq.rabbitmq.MessageData;

import java.util.UUID;

@Slf4j
public class MQTemplate {

    private final RabbitTemplate rabbitTemplate;
    private final GlobalCorrelationTemplate globalCorrelationTemplate;
    private final PropertiesConfig propertiesConfig;

    @Autowired
    public MQTemplate(RabbitTemplate rabbitTemplate, GlobalCorrelationTemplate globalCorrelationTemplate,
                      PropertiesConfig propertiesConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.globalCorrelationTemplate = globalCorrelationTemplate;
        this.propertiesConfig = propertiesConfig;
    }



    //发送消息
    public<T> void send(T message,DestinationData destination){
        rabbitTemplate.convertAndSend(destination.getExchangeName(),destination.getRoutingKey(),new MessageData<>(message,destination),
                globalCorrelationTemplate.getCorrelation(destination));
    }
    //发送消息
    public<T> void send(T message,DestinationData destination,long version){
        rabbitTemplate.convertAndSend(destination.getExchangeName(),destination.getRoutingKey(),new MessageData<>(message,destination,version),
                globalCorrelationTemplate.getCorrelation(destination));
    }
    //发送消息
    public<T> void send(MessageData<T> messageData){
        rabbitTemplate.convertAndSend(messageData.getDestination().getExchangeName(),messageData.getDestination().getRoutingKey(),messageData,
                globalCorrelationTemplate.getCorrelation(messageData.getDestination()));
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

    //发送延时消息
    public <T> void sendDelay(T messageBody, DestinationData destination, int level){
        DestinationData delayDestination = propertiesConfig.getDelay().getDestinations()[level-1];
        rabbitTemplate.convertAndSend(delayDestination.getExchangeName(),delayDestination.getRoutingKey()
                , new MessageData<>(messageBody,destination,delayDestination,level)
                , globalCorrelationTemplate.getCorrelation(destination));
    }

    //分钟转毫秒
    public String minuteToMillis(int minute){
        return ""+minute*60*1000;
    }

}
