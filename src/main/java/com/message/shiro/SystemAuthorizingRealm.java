package com.message.shiro;

import com.message.entities.User;
import com.message.service.UserService;
import com.message.shiro.exception.ShiroExceptionHandler;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zacard on 14-8-7.
 * 系统安全认证实现类
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken token = (UserToken) authenticationToken;

        logger.info("shiro登录验证开始...");
        SimpleAuthenticationInfo info = null;
        try {
            //验证账号
            User user = userService.getUserByUserName(token.getUsername());
            if (user == null) {
                //throw new UnknownAccountException(); //用户名错误
                throw ShiroExceptionHandler.UNKNOWN_ACCOUNT_EXCEPTION;//用户名不存在
            }
            //授权用户信息
            info = new SimpleAuthenticationInfo(
                    user.getUserName()//用户登录名
                    , user.getPassword()//用户登录密码
                    , getName()//realm name
            );
        } catch (Exception e) {
            logger.error("doGetAuthenticationInfo fail", e);
            //throw new AuthenticationException(message, e);
            throw ShiroExceptionHandler.buildUnifiedException(e);
        }

        /**
         * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配.
         * 具体见CustomHashedCredentialsMatcher.java的doCredentialsMatch()方法
         */
        return info;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        Set<String> roleNames = null;
        try {
            String username = (String) getAvailablePrincipal(principals);
            User user = userService.getUserByUserName(username);
            roleNames = new HashSet<String>(Arrays.asList(user.getRoles().split(",")));
    //      Set<String> roleNames = user.getRoles().split(",");
        } catch (Exception e) {
            logger.error("获取用户权限发生异常" + e.getMessage());
        }
        return new SimpleAuthorizationInfo(roleNames);
    }
}
