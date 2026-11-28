package seek.starter.starter.web.jwt.starter.core;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.common.common.starter.core.LimitStore;
import seek.starter.starter.db.redis.starter.core.RedisHashTemplate;
import seek.starter.starter.support.config.object.web.jwt.JWTRoleData;
import seek.starter.starter.web.jwt.starter.config.PropertiesConfig;

import javax.crypto.SecretKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class SecretKeyStore {

    private final ConcurrentHashMap<String, LimitStore<String>> stringKeyStores = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ConcurrentHashMap<Integer, SecretKey>> secretKeyStores = new ConcurrentHashMap<>();
    private final PropertiesConfig propertiesConfig;
    private final RedisHashTemplate redisHashTemplate;

    @Autowired
    public SecretKeyStore(PropertiesConfig propertiesConfig, RedisHashTemplate redisHashTemplate) {
        this.propertiesConfig = propertiesConfig;
        this.redisHashTemplate = redisHashTemplate;
        //初始化
        for (JWTRoleData jwtRoleData : propertiesConfig.getJwtRoles()) {
            stringKeyStores.put(jwtRoleData.getRoleName(), new LimitStore<>(propertiesConfig.getTokenSecretKeyStoreMax()));
            secretKeyStores.put(jwtRoleData.getRoleName(), new ConcurrentHashMap<Integer, SecretKey>());
        }
    }

    @PostConstruct
    private void init() {
        for (JWTRoleData jwtRoleData : propertiesConfig.getJwtRoles()) {


            redisHashTemplate.getAll(propertiesConfig.getTokenSecretKeyStore(), jwtRoleData.getRoleName());
        }

    }

    private void rebuild() {
        for (JWTRoleData jwtRoleData : propertiesConfig.getJwtRoles()) {}
    }

}
