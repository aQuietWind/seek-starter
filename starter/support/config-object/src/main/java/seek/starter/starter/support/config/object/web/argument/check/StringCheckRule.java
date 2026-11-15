package seek.starter.starter.support.config.object.web.argument.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringCheckRule implements CheckRule<String> {

    private String regex;
    private boolean ableNull=false;
    private boolean ableEmpty=false;
    private boolean needTrim=true;
    //可取等
    private int maxAndEqualLength=0;
    //可取等
    private int minAndEqualLength=0;

    public StringCheckRule(String regex) {
        this.regex = regex;
    }
    public StringCheckRule(String regex,boolean ableNull,boolean ableEmpty,boolean needTrim) {
        this.regex = regex;
        this.ableNull = ableNull;
        this.ableEmpty = ableEmpty;
        this.needTrim = needTrim;
    }
    public StringCheckRule(boolean ableNull,boolean ableEmpty,boolean needTrim,int maxAndEqualLength,int minAndEqualLength) {
        this.ableNull = ableNull;
        this.ableEmpty = ableEmpty;
        this.needTrim = needTrim;
        this.maxAndEqualLength = maxAndEqualLength;
        this.minAndEqualLength = minAndEqualLength;
    }

    public boolean check(String arg) {
        //校验是否为空
        if (!ableNull&&arg==null)return false;
        if (arg==null)return true;
        //是否需要trim
        if (needTrim)arg = arg.trim();
        if (!ableEmpty){
            if (arg.isBlank())return false;
        }
        //判断正则是否存在可使用
        if (regex!=null)return arg.matches(regex);
        else return arg.length()<=maxAndEqualLength&&arg.length()>=minAndEqualLength;
    }
}
