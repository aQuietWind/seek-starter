package seek.starter.starter.cloud.feign.starter.configure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.cloud.feign.starter.enums.PackageEnum;


@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesConfig {
    private String requestHeaderTokenIdName="token-id";
    private SentinelAuthority sentinelAuthority=new SentinelAuthority();


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SentinelAuthority {
        private String sentinelAuthorityHeaderName="x-sentinel-authority";
        private String sentinelAuthorityHeaderValue="service";
    }

}
