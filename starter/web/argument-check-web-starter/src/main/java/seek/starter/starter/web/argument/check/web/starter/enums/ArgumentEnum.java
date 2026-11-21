package seek.starter.starter.web.argument.check.web.starter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public enum ArgumentEnum{
    Phone(CheckEnum.Phone,String.class),
    Name(CheckEnum.Name,String.class),
    Text(CheckEnum.Text,String.class),
    Description(CheckEnum.Description,String.class),
    RealAddress(CheckEnum.RealAddress,String.class),
    Password(CheckEnum.Password,String.class),
    CommonId(CheckEnum.CommonId,Long.class),
    Cost(CheckEnum.Cost, Double.class),
    Image(CheckEnum.Image, MultipartFile.class),
    TxtFile(CheckEnum.TxtFile,String.class);


    public final String checkName;
    public final Class<?> parameterClass;

    public static class CheckEnum{
        public static final String Phone="phone";
        public static final String Name="name";
        public static final String Text="text";
        public static final String Description="description";
        public static final String RealAddress="realAddress";
        public static final String Password="password";
        public static final String CommonId="commonId";
        public static final String Cost="cost";
        public static final String Image="image";
        public static final String TxtFile="txtFile";
    }

}
