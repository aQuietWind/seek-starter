package seek.starter.starter.support.config.object.web.argument.check;

import lombok.Data;
import lombok.NoArgsConstructor;
import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;

@Data
@NoArgsConstructor
public class DoubleCheckRule implements CheckRule<Double>{

    private boolean ableNull=false;
    //可取等
    private double maxAndEqual;
    //可取等
    private double minAndEqual=0;
    private int scaleNumber;
    private String regex;

    public DoubleCheckRule(boolean ableNull, double maxAndEqual, double minAndEqual, int scaleNumber){
        this.ableNull=ableNull;
        this.maxAndEqual=maxAndEqual;
        this.minAndEqual=minAndEqual;
        this.scaleNumber=scaleNumber;
        if (this.scaleNumber<=0)throw new BizException(ErrorCodeEnum.DATA_NOT_RIGHT);
        regex="\\d{0,}.\\d{0,"+scaleNumber+"}";
    }

    @Override
    public boolean check(Double arg){
        //非空校验
        if (!ableNull&&arg==null)return false;
        if (arg==null)return true;
        //校验位数
        if (!arg.toString().matches(regex))return false;
        return arg<=maxAndEqual&&arg>=minAndEqual;
    }
}
