package seek.starter.starter.web.argument.check.web.starter.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import seek.starter.starter.support.config.object.web.argument.check.FileCheckRule;

@Data
@AllArgsConstructor
public class MultiPartFileCheckRule extends FileCheckRule<MultipartFile> {

    private String[] types;
    private long maxMb;

    @Override
    public boolean check(MultipartFile arg) {
        return false;
    }







}
