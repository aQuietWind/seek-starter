package seek.starter.starter.db.redis.starter.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedissonAutoConfig.class)
public @interface RedissonImport {}
