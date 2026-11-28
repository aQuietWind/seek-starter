package seek.starter.starter.support.config.object.web.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestContext {
    private String traceId;
    private String tokenId;
    private String requestPath;
    private String ipAddress;
    private String role;
    private String token;
    private int tokenVersion=-1;
    private boolean ableSkip=false;
}
