package seek.starter.starter.common.common.starter.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Container <T>{
    private int version;
    private T data;
}
