package seek.starter.starter.mq.rabbitmq.error.system.starter.configuration;

import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seek.starter.starter.mq.rabbitmq.starter.configuration.PropertiesConfig;

@Configuration
public class ConverterConfig {

    private final PropertiesConfig propertiesConfig;

    public ConverterConfig(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        //添加根包
        typeMapper.setTrustedPackages(propertiesConfig.getConverterPackage());
        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }
}
