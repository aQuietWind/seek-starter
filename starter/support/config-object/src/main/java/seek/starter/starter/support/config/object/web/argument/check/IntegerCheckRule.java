package seek.starter.starter.support.config.object.web.argument.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntegerCheckRule implements CheckRule<Integer>{

    private boolean ableNull=false;
    //可取等
    private int maxAndEqual;
    //可取等
    private int minAndEqual=0;

    public boolean check(Integer arg) {
        if (!ableNull&&arg==null)return false;
        if (arg==null)return true;
        else return arg<=maxAndEqual&&arg>=minAndEqual;
    }
}
