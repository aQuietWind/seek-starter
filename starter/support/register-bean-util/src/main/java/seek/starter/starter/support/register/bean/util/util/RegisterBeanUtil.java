package seek.starter.starter.support.register.bean.util.util;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;


public class RegisterBeanUtil {

    /**
     * 批量按类名注册 bean。
     * <p>
     * 普通类直接注册为 bean 定义；{@code @Configuration} 配置类注册后
     * 会重新触发 {@link ConfigurationClassPostProcessor}（CCPP）解析，
     * 使其 @Bean 方法、@Import 等与常规配置类行为完全一致。
     *
     * @param classes 需要注册的类集合
     * @return 可通过 @Bean 声明的后置处理器
     */
    public static BeanDefinitionRegistryPostProcessor quickRegistrarByClassName(Class<?> ... classes) {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
                for (Class<?> beanClass : classes) {
                    registry.registerBeanDefinition(beanClass.getName(),
                            BeanDefinitionBuilder.genericBeanDefinition(beanClass).getBeanDefinition());
                }
            }

            @Override
            public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
                BeanDefinitionRegistryPostProcessor.super.postProcessBeanFactory(beanFactory);
            }
        };
    }

}
