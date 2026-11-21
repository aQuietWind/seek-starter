package seek.starter.starter.support.config.object.mq.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message <T> {
    private T message;
    private DestinationData destination;
    private Integer retryCount=0;
    private Integer redeliverCount=0;
    private boolean delayMessage=false;
    private Integer delayMillis=null;
    private Long version=null;
    public Message(T message){
        this.message = message;
    }
    public Message(T message, DestinationData destination){
        this.message = message;
        this.destination = destination;
    }

    public boolean validVersion(long otherVersion){
        if (version==null)return false;
        return version>otherVersion;
    }
}
