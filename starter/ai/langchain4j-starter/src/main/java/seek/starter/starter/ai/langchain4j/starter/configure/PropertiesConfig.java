package seek.starter.starter.ai.langchain4j.starter.configure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.ai.langchain4j.starter.enums.PackageEnum;
import seek.util.common.config.object.db.redis.RedisKeyData;


@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesConfig {

    private RedisSessionMemory redisSessionMemory=new RedisSessionMemory();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RedisSessionMemory{
        private Integer storeMax=20;
        private RedisKeyData redisKey=new RedisKeyData("seek:util:langchain4j:memory:", 604800000L);
    }

}
