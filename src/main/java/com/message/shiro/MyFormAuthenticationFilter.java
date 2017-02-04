package com.message.shiro;

import com.message.shiro.exception.ShiroExceptionHandler;
import com.message.util.RandomValidateCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hpj
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    protected final Logger logger = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

    @Autowired
    private ShiroLoginHandler shiroLoginHandler;

    /**
     * 收集登录表单提交的的一些信息
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request,
                                              ServletResponse response) {

        logger.info("初始化token...");
        String username = getUsername(request);
        String password = getPassword(request);
        String host = getHost(request);
        boolean rememberMe = isRememberMe(request);
        String captcha = WebUtils.getCleanParam(request, RandomValidateCode.VALIDATE_CODE_NAME);

        if (password == null) {
            password = "";
        }

        return new UserToken(username, password.toCharArray(), rememberMe,
                host, captcha);
    }

    /**
     * 登录失败处理
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        logger.error("登录失败.");
        request.setAttribute("errorCode", ShiroExceptionHandler.getErrorCode(e));
        request.setAttribute(ShiroExceptionHandler.ERROR_MESSAGE_KEY_ATTRIBUTE_NAME, ShiroExceptionHandler.buildViewErrorMsg(e));

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

        //先判断是否为ajax请求
        if (shiroLoginHandler.isAjaxRequest(httpServletRequest)) {
            shiroLoginHandler.buildAjaxFailAndReturn(httpServletRequest, httpServletResponse, e);
            return false;
        }

        //login failed, let request continue back to the login page:
        return true;
    }

    /**
     * 登录成功的一些处理
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token,
                                     Subject subject, ServletRequest request, ServletResponse response)
            throws Exception {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

        UserToken userToken = (UserToken) token;
        //处理登录成功
        shiroLoginHandler.handlerServiceLoginSuccess(httpServletRequest, httpServletResponse, userToken);

        //如果是ajax就不用继续了
        if (shiroLoginHandler.isAjaxRequest(httpServletRequest)) {
            return false;
        }

        return super.onLoginSuccess(token, subject, request, response);
    }

    /**
     * 认证失败处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }

            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

            //如果是ajax请求，则返回json体(这里一般是seesion过期或者是cookie被清除)
            if (shiroLoginHandler.isAjaxRequest(httpServletRequest)) {
                //shiroLoginHandler.buildAjaxFailAndReturn(httpServletRequest, httpServletResponse, ShiroExceptionHandler.SESSION_TIMEOUT);
                //使用错误状态码的方式,提示客户端,需要重新登录
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

}
