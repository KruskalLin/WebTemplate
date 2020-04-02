package stg.template.template.config;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    int NOT_LIMITED = 0;

    @AliasFor("qps") double value() default NOT_LIMITED;

    @AliasFor("value") double qps() default NOT_LIMITED;

    int timeout() default 0;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
