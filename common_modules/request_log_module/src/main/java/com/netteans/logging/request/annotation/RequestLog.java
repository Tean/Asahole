package com.netteans.logging.request.annotation;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequestLog {
    RequestMethod[] logMethod() default {RequestMethod.GET, RequestMethod.POST};
}
