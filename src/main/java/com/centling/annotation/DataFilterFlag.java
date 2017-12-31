package com.centling.annotation;

import java.lang.annotation.*;

/**
 * Created by lin on 17-9-5.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilterFlag {
    String key() default "";
}
