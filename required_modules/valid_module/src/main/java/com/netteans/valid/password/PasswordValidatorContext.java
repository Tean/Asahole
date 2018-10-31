package com.netteans.valid.password;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Tean on 2016/10/9.
 */
public class PasswordValidatorContext implements ConstraintValidator<Password, String> {
    private final static Logger logger = LoggerFactory.getLogger(PasswordValidatorContext.class);
    private Password constraint;

    private IPasswordValidation passwordValidation;

    public void initialize(Password constraint) {
        logger.info("initialize valid {}", constraint);
        this.constraint = constraint;
        Constructor<?>[] constructors = constraint.passwordValidator().getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            constructor.setAccessible(true);
            try {
                this.passwordValidation = (IPasswordValidation) constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public boolean isValid(String password, ConstraintValidatorContext context) {
        Class<? extends IPasswordValidation> validationClass = constraint.passwordValidator();

        return passwordValidation.validPassWord(password, constraint);
    }
}
