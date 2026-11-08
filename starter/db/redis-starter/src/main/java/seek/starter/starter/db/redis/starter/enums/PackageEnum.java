package seek.starter.starter.db.redis.starter.enums;

public class PackageEnum {
    public static final String Full_Package="seek.starter.starter.db.redis.starter";
    public static final String Full_Package_Prefix=Full_Package+".";

    public static String appendByFullPackage(String other){
        return Full_Package_Prefix+other;
    }
}
