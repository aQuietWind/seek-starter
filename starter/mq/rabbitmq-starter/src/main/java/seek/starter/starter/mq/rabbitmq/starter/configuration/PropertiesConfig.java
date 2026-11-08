package seek.starter.starter.mq.rabbitmq.starter.configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.support.config.object.mq.rabbitmq.BindingData;
import seek.starter.starter.support.config.object.mq.rabbitmq.ExchangeData;

@ConfigurationProperties("seek.util.rabbitmq.config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {
    private ExchangeData errorExchange=new ExchangeData("ex.error",ExchangeData.Fanout);
    private BindingData errorBinding=new BindingData(errorExchange,"q.error",null);
    private String converterPackage="seek.util";
}
