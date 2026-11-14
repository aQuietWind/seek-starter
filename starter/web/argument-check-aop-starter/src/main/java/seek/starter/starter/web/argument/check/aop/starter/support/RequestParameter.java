package seek.starter.starter.web.argument.check.aop.starter.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestParameter {
    private String name;
    private String checkName;
}
