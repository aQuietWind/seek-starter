package seek.starter.starter.web.jwt.starter.enums;

public class PackageEnum {
    public static final String Full_Package="seek.starter.starter.mq.rabbitmq.starter";
    public static final String Full_Package_Prefix=Full_Package+".";

    public static String appendByFullPackage(String other){
        return Full_Package_Prefix+other;
    }
}
