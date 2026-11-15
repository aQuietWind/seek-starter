package seek.starter.starter.support.config.object.web.argument.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongCheckRule implements CheckRule<Long>{

    private boolean ableNull=false;
    //可取等
    private long maxAndEqual;
    //可取等
    private long minAndEqual=0;

    public boolean check(Long arg) {
        //非空校验
        if (!ableNull&&arg==null)return false;
        if (arg==null)return true;
        else return arg<=maxAndEqual&&arg>=minAndEqual;
    }
}
