package seek.starter.starter.db.elasticsearch.starter.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import seek.starter.starter.db.elasticsearch.starter.configure.EsConfig;
import seek.starter.starter.db.elasticsearch.starter.core.EsSearchTemplate;
import seek.starter.starter.db.elasticsearch.starter.core.EsUpdateTemplate;
import seek.starter.starter.db.elasticsearch.starter.enums.PackageEnum;
import seek.starter.starter.support.register.bean.util.util.RegisterBeanUtil;

@AutoConfigureBefore(ElasticsearchDataAutoConfiguration.class)
@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
@Import(EsConfig.class)
public class AutoConfig {

    @Bean(PackageEnum.Full_Package_Prefix +"bean.definition")
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return RegisterBeanUtil.quickRegistrarByClassName(EsSearchTemplate.class, EsUpdateTemplate.class);
    }
}
