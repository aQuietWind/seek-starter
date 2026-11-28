package seek.starter.starter.support.config.object.web.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTRoleData {
    private String roleName;
    private String headerSign;
    private String subject;
    private Long tokenDurationMillis;
}
