package seek.starter.starter.db.redis.starter.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import seek.starter.starter.db.redis.starter.config.PropertiesConfig;
import seek.starter.starter.db.redis.starter.core.Redisson.RedissonTemplate;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seek.starter.starter.db.redis.starter.enums.PackageEnum;

//此处可能属于过度引入redisson，可以将redisson的工具封装拆出来，作为另外一个模块使用
@Configuration@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties({PropertiesConfig.class})
public class RedissonAutoConfig {

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
    public RedissonTemplate redissonUtil(RedissonClient redissonClient) {
        return new RedissonTemplate(redissonClient);
    }
}
