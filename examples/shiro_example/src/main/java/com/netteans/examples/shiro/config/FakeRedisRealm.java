package com.netteans.examples.shiro.config;

import com.netteans.examples.shiro.demo.FedisExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class FakeRedisRealm extends AuthenticatingRealm {

    @Autowired
    private FedisExample fedisExample;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String password = fedisExample.find(username).getPassword();
        return new SimpleAccount(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), "fakeredisrealm");
    }
}
