package com.netteans.valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tean on 2016/10/9.
 */
public class PasswordValid implements ConstraintValidator<Password, String> {
    private final static Logger logger = LoggerFactory.getLogger(PasswordValid.class);
    private Password constraint;

    public void initialize(Password constraint) {
        logger.info("initialize valid {}", constraint);
        this.constraint = constraint;
    }

    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password.length() < constraint.min()) {
            return false;
        }
        if (password.length() > constraint.max()) {
            return false;
        }
        if (constraint.mix() == MixType.WordsAndNumber) {
            if (!scanEng(password) || !scanNumber(password)) {
                return false;
            }
        }
        if (constraint.mix() == MixType.UpperLowerAndNumber) {
            if (!scanEng(password, true) || !scanEng(password, false) || !scanNumber(password)) {
                return false;
            }
        }

        return true;
    }

    private boolean scanNumber(String password) {
        String regex = "[0-9]+?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.find();
    }

    private boolean scanEng(String password, boolean upperCase) {
        String regex = "[a-z]+?";
        if (upperCase)
            regex = "[A-Z]+?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.find();
    }

    private boolean scanEng(String password) {
        String regex = "[a-zA-Z]+?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.find();
    }
}
