package seek.starter.starter.cloud.sentinel.starter.support;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.cloud.sentinel.starter.config.PropertiesConfig;

//汇总请求器
public class UrlSummary implements UrlCleaner {
    private final PropertiesConfig propertiesConfig;
    @Autowired
    public UrlSummary(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    public String clean(String originUrl) {
        // 匹配你服务所有业务接口前缀，统一汇总到全局资源 all_user_service_api
        if (originUrl.startsWith("/")) {
            return propertiesConfig.getAllServiceApiName();
        }
        // 静态资源/特殊路径保留独立资源
        return originUrl;
    }
}
