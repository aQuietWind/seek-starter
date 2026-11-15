package seek.starter.starter.web.argument.check.common.starter.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.support.config.object.web.argument.check.DoubleCheckRule;
import seek.starter.starter.support.config.object.web.argument.check.LongCheckRule;
import seek.starter.starter.support.config.object.web.argument.check.StringCheckRule;
import seek.starter.starter.web.argument.check.aop.starter.enums.PackageEnum;

@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {
    private long idCapacity=10000000000L;
    private StringCheckRule phone=new StringCheckRule("\\d{11}",false,false,false);
    private StringCheckRule name=new StringCheckRule(false,false,true,15,0);
    private StringCheckRule text=new StringCheckRule(false,false,true,2000,0);
    private StringCheckRule description=new StringCheckRule(true,true,true,500,0);
    private StringCheckRule realAddress=new StringCheckRule(true,true,true,50,0);
    private StringCheckRule password=new StringCheckRule(false,false,false,20,0);
    private LongCheckRule commonId=new LongCheckRule(false,2*idCapacity,idCapacity);
    private DoubleCheckRule cost=new DoubleCheckRule(false,10000,0,2);
}
