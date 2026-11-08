package seek.starter.starter.support.config.object.common.caffeine;

import lombok.Data;

@Data
public class CaffeineData {
    private int maxSize;
    private long expireSeconds;
}
