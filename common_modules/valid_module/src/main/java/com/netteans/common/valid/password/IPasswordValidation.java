package com.netteans.common.valid.password;

/**
 * @author netteans
 */
public interface IPasswordValidation {

    /**
     * 密码验证
     * @param password 密码
     * @param context 校验规则
     * @return 校验结果: true 通过；false 不通过
     */
    boolean validPassWord(String password, Password context);
}
