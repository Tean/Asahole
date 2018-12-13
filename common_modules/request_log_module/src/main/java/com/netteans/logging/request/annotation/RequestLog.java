package com.netteans.logging.request.annotation;

import org.springframework.web.bind.annotation.RequestMethod;

public @interface RequestLog {
    RequestMethod[] logMethod() default {RequestMethod.GET, RequestMethod.POST};

}
