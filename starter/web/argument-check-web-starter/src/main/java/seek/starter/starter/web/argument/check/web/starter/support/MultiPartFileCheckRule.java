package seek.starter.starter.web.argument.check.web.starter.support;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import seek.starter.starter.support.config.object.common.common.object.exception.BizException;
import seek.starter.starter.support.config.object.common.common.object.exception.ErrorCodeEnum;
import seek.starter.starter.support.config.object.web.argument.check.FileCheckRule;

import java.util.Arrays;
import java.util.HashSet;

@AllArgsConstructor
@Setter
@Getter
public class MultiPartFileCheckRule extends FileCheckRule<MultipartFile> {
    private HashSet<String> types;
    private long byteMaxSize;
    private boolean ableNull=false;

    public MultiPartFileCheckRule(long byteMaxSize, String ... type){
        this.byteMaxSize = byteMaxSize;
        this.types=new HashSet<>(Arrays.asList(type));
    }

    @Override
    public boolean check(MultipartFile arg) {
        System.err.println(3131);
        System.err.println(types);
        System.err.println(byteMaxSize);
        //检查文件是否为空
        if (arg==null||arg.isEmpty()){
            if (ableNull)return true;
            else throw new BizException(ErrorCodeEnum.FILE_IS_EMPTY);
        }
        //检查文件大小
        if (arg.getSize() > byteMaxSize) throw new BizException(ErrorCodeEnum.TOO_BIG_FILE);
        //检测头像的后缀名
        if (!types.contains(getType(arg))) throw new BizException(ErrorCodeEnum.ERROR_FILE_TYPE);
        System.err.println("true");
        return true;

    }

    @Nonnull
    private String getType(MultipartFile arg) {
        //检查文件名
        String filename = arg.getOriginalFilename();
        if (filename == null || filename.isBlank()) throw new BizException(ErrorCodeEnum.ERROR_FILE_NAME);
        //获取后缀名索引
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1) throw  new BizException(ErrorCodeEnum.ERROR_FILE_TYPE);
        // 统一转小写，避免大小写问题,并且获取类型
        return filename.substring(dotIndex).toLowerCase();
    }


}
