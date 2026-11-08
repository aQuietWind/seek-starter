package seek.starter.starter.mq.kafka.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;
import seek.starter.starter.mq.kafka.starter.config.KafkaConfig;
import seek.starter.starter.mq.kafka.starter.core.KafkaTemplate;
import seek.starter.starter.mq.kafka.starter.core.TopicTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seek.starter.starter.mq.kafka.starter.enums.PackageEnum;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@Import({KafkaConfig.class})
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(KafkaTemplate.class,TopicTemplate.class);
    }
}
