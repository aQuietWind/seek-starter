package seek.util.cloud.gateway.util.enums;

public class PackageEnum {
    public static final String Full_Package="seek.starter.starter.cloud.gateway.starter";
    public static final String Full_Package_Prefix=Full_Package+".";

    public static String appendByFullPackage(String other){
        return Full_Package_Prefix+other;
    }
}
