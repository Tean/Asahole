package com.netteans.example.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @author netteans
 */
public class CustomAuthenticator implements Authenticator {
    @Override
    public AuthenticationInfo authenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo() {
            @Override
            public PrincipalCollection getPrincipals() {
                SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection();
                simplePrincipalCollection.add(authenticationToken.getPrincipal(), "customrealm");
                return simplePrincipalCollection;
            }

            @Override
            public Object getCredentials() {
                return authenticationToken.getCredentials();
            }
        };
        return authenticationInfo;
    }
}
