package com.isl.gbm.user.management.security.shiro.config;

import com.isl.gbm.user.management.dao.PermissionDao;
import com.isl.gbm.user.management.dao.UserDao;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.ldap.UnsupportedAuthenticationMechanismException;
import org.apache.shiro.realm.ldap.DefaultLdapRealm;
import org.apache.shiro.realm.ldap.JndiLdapContextFactory;
import org.apache.shiro.realm.ldap.LdapContextFactory;
import org.apache.shiro.realm.ldap.LdapUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationNotSupportedException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import java.util.HashSet;
import java.util.Properties;

@Component
public class CustomLdapRealm extends DefaultLdapRealm {

    private static final Logger log = LoggerFactory.getLogger(CustomLdapRealm.class);

    @Autowired
    @Lazy
    private UserDao userDao;

    @Autowired
    @Lazy
    private PermissionDao permissionDao;

    @Value("${ldap.domain.name}")
    private String ldapDomainName;

    @Value("${ldap.url}")
    private String ldapURL;

    @Value("${ldap.ssl.url}")
    private String ldapSslURL;

    @Value("${ldap.ssl.enable:NO}")
    private String ldapSslEnable;

    @Value("${ldap.security.authenticationType:simple}")
    private String ldapAuthenticationType;

    @Value("${ldap.trustStore:}")
    private String trustStore;

    @Value("${ldap.trustStorePassword:}")
    private String trustStorePassword;

    @Value("${ldap.securityProtocol:ssl}")
    private String securityProtocol;

    /** ---------------- Authorization ---------------- */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(new HashSet<>(permissionDao.getPermissionName()));
        return info;
    }

    /** ---------------- Authentication ---------------- */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        log.info("Attempting LDAP authentication for username: {}", token.getUsername());

        try {
            return queryForAuthenticationInfo(token, getContextFactory());
        } catch (AuthenticationNotSupportedException e) {
            throw new UnsupportedAuthenticationMechanismException("Unsupported authentication mechanism", e);
        } catch (javax.naming.AuthenticationException e) {
            throw new AuthenticationException("Invalid Username/Password", e);
        } catch (NamingException e) {
            throw new AuthenticationException("LDAP naming error while attempting to authenticate user", e);
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException("Unknown account", e);
        } catch (IncorrectCredentialsException e) {
            throw new IncorrectCredentialsException("Incorrect credentials", e);
        }
    }

    @Override
    protected AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken token, LdapContextFactory ldapContextFactory)
            throws NamingException {

        Object principal = getLdapPrincipal(token);
        Object credentials = token.getCredentials();
        LdapContext ctx = null;

        try {
            // Build user principal with domain
            String username = token.getPrincipal() + "@" + ldapDomainName;

            log.info("Binding LDAP user: {}", username);

            ctx = ldapContextFactory.getLdapContext(username, credentials);

            log.debug("LdapContext obtained: {}", ctx);

            return createAuthenticationInfo(token, principal, credentials, ctx);

        } finally {
            LdapUtils.closeContext(ctx);
        }
    }

    @Override
    public LdapContextFactory getContextFactory() {
        JndiLdapContextFactory factory = new JndiLdapContextFactory();

        if ("NO".equalsIgnoreCase(ldapSslEnable)) {
            log.info("Using non-SSL LDAP URL: {}", ldapURL);
            factory.setUrl(ldapURL);
        } else {
            log.info("Using SSL LDAP URL: {}", ldapSslURL);
            factory.setUrl(ldapSslURL);
            factory.getEnvironment().put("java.naming.security.protocol", securityProtocol);
            if (!trustStore.isEmpty()) {
                System.setProperty("javax.net.ssl.trustStore", trustStore);
                System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
            }
        }
        factory.getEnvironment().put("java.naming.security.authentication", ldapAuthenticationType);

        return factory;
    }
}
