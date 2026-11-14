package seek.starter.starter.db.redis.starter.configure;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.db.redis.starter.enums.PackageEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
public class PropertiesConfig {
}
