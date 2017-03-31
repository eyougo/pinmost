package com.pinmost.api.web.spring.version;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: mei
 * Date: 3/5/16
 * Time: 22:17
 */
@RequestMapping // Required as otherwise Spring won't combine the configuration if the class or method is not already annotated with RequestMapping
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMinVersion {

    int DEFAULT = 1;

    int value() default DEFAULT;
}
