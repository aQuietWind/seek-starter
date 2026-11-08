package seek.starter.starter.web.web.starter.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfigImpl implements WebMvcConfigurer {            //实现
    @Autowired          //依赖注入
    private TokenInterceptor tokenInterceptor;          //获取拦截器的对象
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**");        //添加拦截器，且声明该拦截器将拦截所有请求，"/*"只能匹配一级路径，不能匹配"/xx1/xx2/..."

    }
}
