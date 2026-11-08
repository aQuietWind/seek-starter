package seek.starter.starter.cloud.sentinel.starter.support;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.cloud.sentinel.starter.config.PropertiesConfig;


//请求头名单判别，用于Service模块对于sentinel的请求过滤
public class HeaderOriginParser implements RequestOriginParser {
    private final PropertiesConfig propertiesConfig;
    @Autowired
    public HeaderOriginParser(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    public String parseOrigin(HttpServletRequest request){
        //可改为nacos_config统一配置,注意，这个请求头名字不要用origin，不然feign请求时会被吞掉
        String origin = request.getHeader(propertiesConfig.getAuthority().getRequestHeaderName());
        //设置为拒绝标识
        if (origin == null|| origin.isEmpty()) return "blank";
        //重新返回该请求头标识
        return origin;
    }
}
