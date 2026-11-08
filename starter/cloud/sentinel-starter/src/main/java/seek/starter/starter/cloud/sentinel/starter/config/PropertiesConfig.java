package seek.starter.starter.cloud.sentinel.starter.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.cloud.sentinel.starter.enums.PackageEnum;


@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesConfig {
    private String allServiceApiName="allServiceApi";
    private Authority authority;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Authority{
        private String requestHeaderName="x-sentinel-authority";
    }


}
