package seek.starter.starter.web.argument.check.aop.starter.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum CheckLevel {
    Custom(0),
    Extension(1),
    Default(2);

    private int level;

    public static boolean priorOrEqual(CheckLevel biggerOne,CheckLevel smallerOne){
        return biggerOne.level>=smallerOne.level;
    }
}
