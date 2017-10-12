package com.dlq.blog.db.annotation;

import com.dlq.blog.db.DBWRType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 读、写注解
 * @author donglq
 * @date 2017/10/5 1:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DBWR {

    DBWRType value() default DBWRType.WRITE;

}
