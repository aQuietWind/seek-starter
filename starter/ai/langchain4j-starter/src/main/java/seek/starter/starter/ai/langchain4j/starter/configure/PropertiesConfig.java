package seek.starter.starter.ai.langchain4j.starter.configure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.util.common.config.object.db.redis.RedisKeyData;


@ConfigurationProperties("seek.util.ai.langchain4j.config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesConfig {
    public static final String Full_Package = "seek.starter.starter.ai.langchain4j.starter";

    private RedisSessionMemory redisSessionMemory=new RedisSessionMemory();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RedisSessionMemory{
        private Integer storeMax=20;
        private RedisKeyData redisKey=new RedisKeyData("seek:util:langchain4j:memory:", 604800000L);
    }

}
