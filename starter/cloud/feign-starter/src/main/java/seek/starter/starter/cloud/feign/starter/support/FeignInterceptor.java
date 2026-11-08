package seek.starter.starter.cloud.feign.starter.support;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import seek.starter.starter.cloud.feign.starter.configure.PropertiesConfig;
import seek.starter.starter.support.config.object.common.context.TokenIdContext;

@Slf4j
public class FeignInterceptor implements RequestInterceptor {

    private final PropertiesConfig propertiesConfig;
    @Autowired
    public FeignInterceptor(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    public void apply(RequestTemplate template) {
            //获取当前Http上下文，并检查
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null)return;
            //从tokenId线程上下文中取出tokenId
            String tokenId = TokenIdContext.get();
            log.info("tokenId:{} ,调用了feign",tokenId);
            if (tokenId != null && !tokenId.isBlank()) {
                //Feign发起远程调用时自动带上该Header
                template.header(propertiesConfig.getRequestHeaderTokenIdName(), tokenId);
                //用于sentinel检测
                template.header(propertiesConfig.getSentinelAuthority().getSentinelAuthorityHeaderName()
                        , propertiesConfig.getSentinelAuthority().getSentinelAuthorityHeaderValue());
            }
    }
}