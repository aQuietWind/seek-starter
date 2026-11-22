package seek.starter.starter.support.config.object.mq.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageData<T> {
    private T message;
    private DestinationData destination;
    private Integer retryCount=0;
    private Integer redeliverCount=0;
    private Integer ttl=null;
    private DestinationData delayDestination=null;
    private Integer delayLevel=null;
    private Long version=null;
    public MessageData(T message){
        this.message = message;
    }
    public MessageData(T message, DestinationData destination){
        this.message = message;
        this.destination = destination;
    }
    public MessageData(T message, DestinationData destination,long version){
        this.message = message;
        this.destination = destination;
        this.version=version;
    }
    public MessageData(T message,DestinationData destination,DestinationData delayDestination,int delayLevel){
        this.message = message;
        this.destination = destination;
        this.delayDestination=delayDestination;
        this.delayLevel=delayLevel;
    }
    public MessageData(T message,DestinationData destination,DestinationData delayDestination,int delayLevel,Long version){
        this.message = message;
        this.destination = destination;
        this.delayDestination=delayDestination;
        this.delayLevel=delayLevel;
        this.version=version;
    }

    public boolean validVersion(long otherVersion){
        if (version==null)return false;
        return version>otherVersion;
    }
}
