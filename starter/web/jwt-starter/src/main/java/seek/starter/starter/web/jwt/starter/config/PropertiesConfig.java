package seek.starter.starter.web.jwt.starter.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.support.config.object.db.redis.RedisKeyData;
import seek.starter.starter.support.config.object.web.jwt.JWTRoleData;
import seek.starter.starter.web.jwt.starter.enums.PackageEnum;

import java.util.HashMap;


@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {

    private JWTGlobalData jwtGlobalData=new JWTGlobalData();
    private RedisKeyData tokenStore=new RedisKeyData("jwt:token:store:",null);
    private RedisKeyData tokenSecureKeyStore=new RedisKeyData("jwt:secureKey:store",null);
    private RedisKeyData tokenVersionStore=new RedisKeyData("jwt:secureKey:version:",null);
    private JWTRoleData[] jwtRoles;

    private final HashMap<String,JWTRoleData> jwtRolesMap=new HashMap<>();



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
