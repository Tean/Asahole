package com.netteans.example.gof.proxy;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author netteans
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bothering {
    String value() default "I feel tired";
}
