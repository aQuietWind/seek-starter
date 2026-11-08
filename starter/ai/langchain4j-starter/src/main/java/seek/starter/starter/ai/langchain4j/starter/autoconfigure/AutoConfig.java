package seek.starter.starter.ai.langchain4j.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import seek.starter.starter.ai.langchain4j.starter.configure.PropertiesConfig;
import seek.starter.starter.ai.langchain4j.starter.core.ChatTemplate;
import seek.starter.starter.ai.langchain4j.starter.enums.PackageEnum;
import seek.starter.starter.ai.langchain4j.starter.support.RedisChatMemoryStore;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@EnableConfigurationProperties(PropertiesConfig.class)
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(ChatTemplate.class, RedisChatMemoryStore.class);
    }
}