package com.netteans.domain;

import com.netteans.valid.MixType;
import com.netteans.valid.Password;

import javax.validation.constraints.NotBlank;

/**
 * Created by Tean on 2016/10/9.
 */
public class DemoUser {
    @NotBlank(message = "用户名不能为空")
    private String name;
    @Password(min = 6, max = 20, mix = MixType.UpperLowerAndNumber, message = "密码必须在6-20位长度之间，并且数字大小写英文混合")
    private String password;

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

    @Override
    public String toString() {
        return String.format("%s:%s", super.toString(), "{\"Name\":\"" + name + "\",\"Password\":\"" + password + "\"}");
    }
}
