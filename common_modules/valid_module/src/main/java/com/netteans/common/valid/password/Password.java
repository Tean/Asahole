package com.netteans.common.valid.password;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Tean on 2016/10/9.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidatorContext.class)
public @interface Password {
    int min() default 0;

    int max() default 2147483647;

    MixType mix() default MixType.NoMix;

    String message() default "密码校验";

    Class<? extends IPasswordValidation> passwordValidator();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        Length[] value();
    }
}
