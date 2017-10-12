package com.dlq.blog.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @author donglq
 * @date 2017/10/3 23:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Router {

    /**
     * 做路由的字段名
     * @return
     */
    String routerField();

    /**
     * 规则bean名
     * @return
     */
    String ruleBeanName();

}
