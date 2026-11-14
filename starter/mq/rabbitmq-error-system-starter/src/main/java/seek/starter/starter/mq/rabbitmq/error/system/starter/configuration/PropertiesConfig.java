package seek.starter.starter.mq.rabbitmq.error.system.starter.configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.mq.rabbitmq.starter.enums.PackageEnum;
import seek.starter.starter.support.config.object.mq.rabbitmq.BindingData;
import seek.starter.starter.support.config.object.mq.rabbitmq.ExchangeData;

@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {
    public static final String ErrorQueueDestination="${"+PackageEnum.Full_Package_Prefix+"config.error-binding.queue-name}";
    private ExchangeData errorExchange=new ExchangeData("ex.error",ExchangeData.Fanout);
    private BindingData errorBinding=new BindingData(errorExchange,"q.error",null);
}
