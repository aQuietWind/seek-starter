package seek.starter.starter.db.redis.redisson.starter.configure;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.db.redis.redisson.starter.enums.PackageEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
public class PropertiesConfig {
    private RedisConnection redissonConnection=new RedisConnection();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedisConnection {
        private String host="localhost";
        private String port="6379";
        private String password="123456";
    }
}
