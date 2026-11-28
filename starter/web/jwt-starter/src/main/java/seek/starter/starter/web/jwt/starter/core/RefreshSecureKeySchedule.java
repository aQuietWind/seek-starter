package seek.starter.starter.web.jwt.starter.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import seek.starter.starter.db.redis.starter.core.RedisTemplate;
import seek.starter.starter.support.config.object.web.jwt.JWTRoleData;
import seek.starter.starter.web.jwt.starter.config.PropertiesConfig;

import java.util.HashMap;

public class RefreshSecureKeySchedule {

    private final PropertiesConfig propertiesConfig;
    private final RedisTemplate redisTemplate;
    private final HashMap<String, JWTRoleData> jwtRoles;
    @Autowired
    public RefreshSecureKeySchedule(PropertiesConfig propertiesConfig, RedisTemplate redisTemplate) {
        this.propertiesConfig=propertiesConfig;
        this.redisTemplate=redisTemplate;
        this.jwtRoles=new HashMap<>();
        for (JWTRoleData jwtRoleData : propertiesConfig.getJwtRoles()) jwtRoles.putIfAbsent(jwtRoleData.getRoleName(), jwtRoleData);
    }

    //每三小时检查一次
    @Scheduled(fixedDelay = 180*60*1000)
    public void refresh(){
        jwtRoles.forEach((roleName,jwtRoleData)->{

        });
    }
}
