package seek.starter.starter.web.argument.check.aop.starter.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.web.argument.check.aop.starter.enums.PackageEnum;

@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {
    private String requestHeaderTokenIdName="tokenId";
}
