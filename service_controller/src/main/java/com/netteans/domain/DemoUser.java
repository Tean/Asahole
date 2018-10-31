package com.netteans.domain;

import com.netteans.common.valid.password.IPasswordValidation;
import com.netteans.common.valid.password.MixType;
import com.netteans.common.valid.password.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tean on 2016/10/9.
 */
public class DemoUser {
    @NotBlank(message = "用户名不能为空")
    private String name;
    @Password(
            min = 6,
            max = 20,
            mix = MixType.UpperLowerAndNumber,
            message = "${valid.password.message}",
            passwordValidator = PasswordValidator.class
    )
    private String password;

    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", super.toString(), "{\"Name\":\"" + name + "\",\"Login\":\"" + password + "\"}");
    }
}

//@Component
class PasswordValidator implements IPasswordValidation {

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

    @Override
    public boolean validPassWord(String password, Password constraint) {
        System.out.println("use DefaultValidator");

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
}
