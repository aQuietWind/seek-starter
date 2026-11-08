package seek.starter.starter.cloud.sentinel.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import seek.starter.starter.cloud.sentinel.starter.enums.PackageEnum;
import seek.starter.starter.cloud.sentinel.starter.support.HeaderOriginParser;
import seek.starter.starter.cloud.sentinel.starter.config.PropertiesConfig;
import seek.starter.starter.cloud.sentinel.starter.support.SentinelBlockHandler;
import seek.starter.starter.cloud.sentinel.starter.support.UrlSummary;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties({PropertiesConfig.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(HeaderOriginParser.class, SentinelBlockHandler.class,UrlSummary.class);
    }

}