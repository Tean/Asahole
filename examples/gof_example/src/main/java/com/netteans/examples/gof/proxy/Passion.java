package com.netteans.examples.gof.proxy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author netteans
 */
@Bothering("I feel passion")
@Retention(RetentionPolicy.RUNTIME)
public @interface Passion {
    boolean passed() default true;
}
