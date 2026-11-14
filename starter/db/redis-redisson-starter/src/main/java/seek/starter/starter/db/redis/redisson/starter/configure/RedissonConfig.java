package seek.starter.starter.db.redis.redisson.starter.configure;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.db.redis.redisson.starter.core.RedissonTemplate;

public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(PropertiesConfig propertiesConfig) {
        String host=propertiesConfig.getRedissonConnection().getHost();
        String port=propertiesConfig.getRedissonConnection().getPort();
        String password=propertiesConfig.getRedissonConnection().getPassword();
        //获取配置对象
        Config config = new Config();
        //设置地址和密码,常规的读取配置会读取空字符串，所以需要进行判断变为null值
        config.useSingleServer().setAddress("redis://"+host+":"+port).setPassword(password.isBlank()?null:password);
        //导入配置，并且返回工具对象给SpringBoot
        return Redisson.create(config);
    }

    @Bean
    public RedissonTemplate redissonTemplate(RedissonClient redissonClient) {
        return new RedissonTemplate(redissonClient);
    }
}
