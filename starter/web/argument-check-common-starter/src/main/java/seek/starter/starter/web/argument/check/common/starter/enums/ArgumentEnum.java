package seek.starter.starter.web.argument.check.common.starter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArgumentEnum{
    Phone("phone",String.class),
    Name("name",String.class),
    Text("text",String.class),
    Description("description",String.class),
    RealAddress("realAddress",String.class),
    Password("password",String.class),
    CommonId("commonId",Long.class),
    Cost("cost", Double.class),
    Image("image",String.class),
    TxtFile("txtFile",String.class),;


    private final String checkName;
    private final Class<?> parameterClass;


}
