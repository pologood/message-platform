package com.message.shiro;

import com.alibaba.fastjson.JSON;
import com.message.shiro.exception.ShiroExceptionHandler;
import com.message.util.EnCryptors;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * shiro登录处理类
 *
 * @author Guoqw
 * @since 2015-08-03 14:01
 */
public class ShiroLoginHandler {

    private static final Logger logger = LoggerFactory.getLogger(ShiroLoginHandler.class);

    //登录成功后的返回
    public static final String LOGIN_SUCCESS = "x.login.success";
    //记住我cookie名称
    public static final String X_COOKIE_USER = "xkeshi.cookie.user";
    //记住我cookie内容分隔符
    public static final String X_COOKIE_SPLIT = ",";
    //默认的记住我的字段名称
    public static final String DEFAULT_REMEMBERME_PARAM = "rememberMe";

    @Value("${shiro.loginUrl}")
    private String loginUrl;//登录url

    @Value("${shiro.successUrl}")
    private String successUrl;//登录成功跳转的url

    @Value("${shiro.usernameParam}")
    private String usernameParam;//登录时用户名的字段名称

    @Value("${shiro.passwordParam}")
    private String passwordParam;//登录时密码的字段名称

    //@Value("${shiro.rememberMeParam}")
    private String rememberMeParam = DEFAULT_REMEMBERME_PARAM;//登录时记住我的字段名称

    /**
     * 服务端强制退出登录
     */
    public void serviceLogout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 服务端登录
     */
    public void serviceLogin(String username, String password) {
        serviceLogin(new UserToken(username, password.toCharArray(), false, "", ""));
    }

    /**
     * 服务端code登录
     */
    public void serviceCodeLogin(String username, String code) {
        serviceLogin(new UserToken(username, passwordParam, false, "", code));
    }

    /**
     * 服务端登录
     */
    public void serviceLogin(UserToken userToken) {
        SecurityUtils.getSubject().login(userToken);
    }

    /**
     * 服务端重新登录
     */
    public void serviceReLogin(String username, String password) {
        //先退出登录
        serviceLogout();
        //登录
        serviceLogin(username, password);
    }

    /**
     * 服务端检测到不合理，重新登录
     * 例如：已经登录了，确重新到登录页面重新登录
     */
    public String serviceReLoginForIllegal(HttpServletRequest request, HttpServletResponse response) {
        //先退出登录
        serviceLogout();
        //登录
        UserToken userToken = new UserToken(WebUtils.getCleanParam(request, usernameParam)
                , WebUtils.getCleanParam(request, passwordParam).toCharArray()
                , WebUtils.isTrue(request, getRememberMeParam()), request.getRemoteAddr(), "");
        try {
            serviceLogin(userToken);
        } catch (Exception e) {
            //登录失败
            return handlerServiceLoginFail(request, response, e);
        }
        //登录成功
        handlerServiceLoginSuccess(request, response, userToken);
        return LOGIN_SUCCESS;
    }


    /**
     * 构建登录成功的ajax返回
     */
    public AjaxResult buildSuccessAjaxResult(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ajaxResult = getAjaxResult();
        ajaxResult.setCode(AjaxResult.LOGIN_SUCCESS);
        //获取回调url
        String callbackUrl = null;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest != null && StringUtils.isNotBlank(savedRequest.getRequestUrl())) {
            callbackUrl = savedRequest.getRequestUrl();
        }
        if (callbackUrl==null) {
            callbackUrl = getSuccessUrl();
        }
        ajaxResult.setUrl(response.encodeURL(callbackUrl));
        return ajaxResult;
    }

    /**
     * 构建登录失败的ajax返回
     */
    public AjaxResult buildFailAjaxResult(HttpServletRequest request, Exception e) {
        AjaxResult ajaxResult = getAjaxResult();
        ajaxResult.setCode(ShiroExceptionHandler.getErrorCode(e));
        ajaxResult.setDescription(ShiroExceptionHandler.buildViewErrorMsg(e));
        ajaxResult.setIsShowCaptcha(ShiroExceptionHandler.needShowCaptcha(request) ? AjaxResult.SHOW_CAPTCHA : AjaxResult.NOT_SHOW_CAPTCHA);
        return ajaxResult;
    }

    /**
     * 返回ajax登录请求结果
     */
    public void ajaxResponse(AjaxResult ajaxResult, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JSON.toJSON(ajaxResult));
            out.flush();
        } catch (Exception e) {
            logger.error("ShiroLoginHandler ajaxResponse fail", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 创建ajax登录失败并返回
     */
    public void buildAjaxFailAndReturn(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ajaxResponse(buildFailAjaxResult(request, e), response);
    }

    /**
     * 创建ajax登录成功并返回
     */
    public void buildAjaxSuccessAndReturn(HttpServletRequest request, HttpServletResponse response) {
        ajaxResponse(buildSuccessAjaxResult(request, response), response);
    }

    /**
     * 是否ajax请求
     */
    public boolean isAjaxRequest(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("XMLHttpRequest", request.getHeader("X-Requested-With"));
    }

    /**
     * 处理服务端登录(调用SecurityUtils.getSubject().login())失败的处理
     * ps:如果不是ajax，返回错误信息
     */
    public String handlerServiceLoginFail(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (isAjaxRequest(request)) {//如果是ajax请求的时候
            buildAjaxFailAndReturn(request, response, e);
        } else {
            //非ajax直接返回错误信息
            return ShiroExceptionHandler.buildViewErrorMsg(e);
        }
        return null;
    }

    /**
     * 处理服务端登录成功的情况
     */
    public void handlerServiceLoginSuccess(HttpServletRequest request, HttpServletResponse response, UserToken userToken) {
        //设置rememberMe cookie
        settingRememberMeCookie(response, userToken);
        //更新登录信息

        //处理ajax返回
        if (isAjaxRequest(request)) {
            buildAjaxSuccessAndReturn(request, response);
        }

    }

    /**
     * 设置remember me cookie
     * ps:设置或者清除
     */
    public void settingRememberMeCookie(HttpServletResponse response, UserToken userToken) {
        if (userToken.isRememberMe()) {//记住我
            String cookieValue = userToken.getUsername() + X_COOKIE_SPLIT + String.valueOf(userToken.getPassword());
            Cookie cookie = new Cookie(X_COOKIE_USER, EnCryptors.AES.encrypt(cookieValue));
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 60 * 24 * 30);//30天
            response.addCookie(cookie);
        } else {
            //清除cookie
            Cookie cookie = new Cookie(X_COOKIE_USER, StringUtils.EMPTY);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }


    public String getLoginUrl() {
        return loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public String getUsernameParam() {
        return usernameParam;
    }

    public String getPasswordParam() {
        return passwordParam;
    }

    public String getRememberMeParam() {
        return rememberMeParam;
    }

    public AjaxResult getAjaxResult() {
        return new AjaxResult();
    }

    //ajax请求登录的返回实体
    public class AjaxResult {

        public static final String LOGIN_SUCCESS = "0";
        public static final String LOGIN_FAIL = "1";

        public static final String SHOW_CAPTCHA = "true";
        public static final String NOT_SHOW_CAPTCHA = "false";

        private String code;//是否登录成功:0_成功|1_失败
        private String url;//登录成功后的跳转url
        private String description;//失败描述
        private String isShowCaptcha;//是否显示验证码
        private Object datas;//其他数据

        public AjaxResult() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIsShowCaptcha() {
            return isShowCaptcha;
        }

        public void setIsShowCaptcha(String isShowCaptcha) {
            this.isShowCaptcha = isShowCaptcha;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getDatas() {
            return datas;
        }

        public void setDatas(Object datas) {
            this.datas = datas;
        }
    }
}
