package seek.starter.starter.web.jwt.starter.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.support.config.object.db.redis.RedisKeyData;
import seek.starter.starter.support.config.object.web.jwt.JWTRoleData;
import seek.starter.starter.web.jwt.starter.enums.PackageEnum;



@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {

    private JWTGlobalData jwtGlobalData=new JWTGlobalData();
    private RedisKeyData tokenStore=new RedisKeyData("jwt:token:store:",null);
    private RedisKeyData tokenSecretKeyStore =new RedisKeyData("jwt:secretKey:store:",null);
    private RedisKeyData tokenSecretKeyMessage =new RedisKeyData("jwt:secretKey:store:message:",null);
    private Integer tokenSecretKeyStoreMax =10;
    private JWTRoleData[] jwtRoles;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JWTGlobalData {
        private String tokenHeaderSeparator="-";
        private String requestHeaderTokenName="seek-token";
        private String requestHeaderTokenIdName="seek-token-id";
        private Integer maxStore=3;
    }
}
