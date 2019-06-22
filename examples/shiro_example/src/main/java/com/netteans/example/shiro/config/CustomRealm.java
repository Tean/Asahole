package com.netteans.example.shiro.config;

import com.netteans.example.shiro.demo.User;
import com.netteans.example.shiro.demo.UserExample;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserExample userExample;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = userExample.find(username).getRole();
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userExample.find(token.getUsername());

        if (user == null) {
            throw new AccountException("用户不存在");
        }

        String password = user.getPassword();
        if (null == password) {
            throw new AccountException("密码不能为空");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        //TODO：如果用户存在于session Cache踢掉缓存的旧用户并替换为新用户，扩展：根据不同的请求头或者扩展踢掉不同的设备或者终端登录用户
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
}
