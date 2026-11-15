package seek.starter.starter.web.argument.check.common.starter.core;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.support.config.object.web.argument.check.CheckRule;
import seek.starter.starter.web.argument.check.aop.starter.core.CheckEntry;
import seek.starter.starter.web.argument.check.aop.starter.core.CheckStore;
import seek.starter.starter.web.argument.check.common.starter.config.PropertiesConfig;
import seek.starter.starter.web.argument.check.common.starter.enums.ArgumentEnum;

public class Stuff {
    private final PropertiesConfig propertiesConfig;
    private final CheckStore checkStore;
    @Autowired
    public Stuff(PropertiesConfig propertiesConfig, CheckStore checkStore) {
        this.propertiesConfig = propertiesConfig;
        this.checkStore = checkStore;
    }
    @PostConstruct
    public void init(){
        checkStore.putIfAbsent(
                quickBuild(ArgumentEnum.Phone, propertiesConfig.getPhone()),
                quickBuild(ArgumentEnum.Name, propertiesConfig.getName()),
                quickBuild(ArgumentEnum.Text, propertiesConfig.getText()),
                quickBuild(ArgumentEnum.Description, propertiesConfig.getDescription()),
                quickBuild(ArgumentEnum.RealAddress, propertiesConfig.getRealAddress()),
                quickBuild(ArgumentEnum.Password, propertiesConfig.getPassword()),
                quickBuild(ArgumentEnum.CommonId, propertiesConfig.getCommonId()),
                quickBuild(ArgumentEnum.Cost, propertiesConfig.getCost())
        );
    }

    public static <T> CheckEntry<T> quickBuild(ArgumentEnum argumentEnum,CheckRule<T> checkRule){
        return CheckEntry.quickBuildDefaultCheckEntry(argumentEnum.checkName, (Class<T>) argumentEnum.parameterClass, checkRule::check);
    }
}
