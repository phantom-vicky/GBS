package com.isl.gbm.user.management.security.shiro.config;

import com.isl.gbm.Utils.CommonConstants;
import com.isl.gbm.user.management.dao.PermissionDao;
import com.isl.gbm.user.management.dao.UserDao;
import com.isl.gbm.user.management.model.Permission;
import com.isl.gbm.user.management.model.User;
import com.isl.gbm.user.management.model.UserDomain;
import com.isl.gbm.user.management.model.UserStatus;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * The Class CustomRealm.
 */
@Service
public class CustomRealm extends AuthorizingRealm {

    /**
     * Instantiates a new custom realm.
     */
/*	public CustomRealm(BcryptPasswordMatcher bcryptPasswordMatcher) {
		setCredentialsMatcher(bcryptPasswordMatcher);
	}*/

    /**
     * The user dao.
     */
    @Autowired
    @Lazy
    private UserDao userDao;

    @Autowired
    @Lazy
    private PermissionDao permissionDao;

    /**
     * Sets the user dao.
     *
     * @param userDao the new user dao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.jdbc.JdbcRealm#doGetAuthorizationInfo(org.apache.shiro
     * .subject.PrincipalCollection)
     */
    @Override

    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*User user = userDao.getUserByUsername(principals.getPrimaryPrincipal().toString());

		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			for (Role role : user.getRoles()) {
				if(role.getDisableRole() != 1) {
					info.addRole(role.getName());
					info.addStringPermissions(getPermissions(role.getPermissions()));
				}
			}

			return info;
		}*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*Set<String> roleSet = userDao.getRoleSet();

        if (roleSet != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            for (String role : roleSet) {
                info.addRole(role);
            }*/
            info.addStringPermissions(new HashSet<>(permissionDao.getPermissionName()));

     /*       return info;
        } else {
            return null;
        }*/
         return info;
    }

    /**
     * Gets the permissions.
     *
     * @param permissions the permissions
     * @return the permissions
     */
    private Set<String> getPermissions(Set<Permission> permissions) {
        Set<String> permissionSet = new HashSet<>();
        for (Permission permission : permissions) {
            permissionSet.add(permission.getDisplayName());
        }
        return permissionSet;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.jdbc.JdbcRealm#doGetAuthenticationInfo(org.apache.
     * shiro.authc.AuthenticationToken)
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userDao.getUserByUsername(token.getPrincipal().toString());
        if (user != null) {
            if (user.getStatus().equals(UserStatus.DISABLED.getId())) {
                throw new DisabledAccountException();
            } else if (user.getStatus().equals(UserStatus.LOCKED.getId())) {
                throw new LockedAccountException();
            } else if (user.getStatus().equals(UserStatus.DELETED.getId())) {
                throw new UnknownAccountException();
            } else if (user.getStatus().equals(UserStatus.EXPIRED.getId())) {
                throw new AccountExpiredException();
            } else if (CommonConstants.YES.equals(user.getLoggedInFlag())) {
                throw new AlreadyAuthenticatedException();
            } else if(UserDomain.FINACLE.getDomain().equals(user.getUserDomain())) {
                throw new FinacleNotIntegratedException();              // After Finacle Integrated it should be removed
            }
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        } else {
            return null;
        }
    }

}
