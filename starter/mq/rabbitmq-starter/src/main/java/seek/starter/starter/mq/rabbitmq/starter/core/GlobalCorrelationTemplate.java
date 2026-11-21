package seek.starter.starter.mq.rabbitmq.starter.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import seek.starter.starter.support.config.object.common.common.object.function.RunWithReturnParamFunction;
import seek.starter.starter.support.config.object.common.common.object.function.RunWithReturnTwoParamFunction;
import seek.starter.starter.support.config.object.common.common.object.function.RunWithTwoParams;
import seek.starter.starter.support.config.object.mq.rabbitmq.DestinationData;

import java.util.UUID;
import java.util.function.BiConsumer;

@Slf4j
@Data
@NoArgsConstructor
public class GlobalCorrelationTemplate {

    private RunWithReturnParamFunction<BiConsumer<CorrelationData.Confirm,Throwable>,DestinationData> getCallback=
            (destination)->
        (confirm, throwable) -> {
            if (throwable != null) {
                log.error("Exchange:{}发送消息到RoutingKey:{},发生异常", destination.getExchangeName(), destination.getRoutingKey());
            }
            if (!confirm.isAck()) {
                log.error("Exchange:{}发送消息到RoutingKey:{},未能成功到达交换机",destination.getExchangeName(), destination.getRoutingKey() );
            }
        };

    public GlobalCorrelationTemplate(RunWithReturnParamFunction<BiConsumer<CorrelationData.Confirm,Throwable>,DestinationData> getCallback) {
        this.getCallback=getCallback;
    }

    public CorrelationData createCorrelationData(DestinationData destination, String correlationId) {
        //生成一个带有随机id的correlationData
        CorrelationData correlationData = new CorrelationData(correlationId);
        //设置回调函数
        correlationData.getFuture().whenComplete(getCallback.run(destination));
        return correlationData;
    }

    //产生CorrelationData
    public CorrelationData getCorrelation(DestinationData destination){
        //生成一个带有随机id的correlationData
        return createCorrelationData(destination, UUID.randomUUID().toString());
    }

    //产生CorrelationData
    public CorrelationData getCorrelationWithId(DestinationData destination, String correlationId){
        //生成一个带有指定id的correlationData
        return createCorrelationData(destination,correlationId);
    }
}
