package com.cdemo.learnspringboot.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoveCacheAnnotation {

    String app() default "colstar";

    String module() default "common";

    /**
     * 缓存key
     *
     * @author: col_star
     * @time: 2021/3/7 10:20
     */
    String key() default "";
}
