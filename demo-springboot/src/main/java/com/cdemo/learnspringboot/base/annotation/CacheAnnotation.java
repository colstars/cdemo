package com.cdemo.learnspringboot.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CacheAnnotation {

    String app() default "colstar";

    String module() default "common";

    /**
     * 缓存key
     *
     * @author: col_star
     * @time: 2021/3/7 10:20
     */
    String key() default "";

    /**
     * 是否缓存空值
     *
     * @author: col_star
     * @time: 2021/3/7 10:21
     */
    boolean cacheNull() default false;

    /**
     * 生存时间，单位是秒，默认为-1(永不过期)
     *
     * @author: col_star
     * @time: 2021/3/7 10:22
     */
    int ttl() default -1;

    /**
     * 生存状态
     * true:每访问一次，将刷新存活时间
     * false:不刷新存活时间，时间一到就清除
     *
     * @author: col_star
     * @time: 2021/3/7 10:22
     */
    boolean state() default true;
}
