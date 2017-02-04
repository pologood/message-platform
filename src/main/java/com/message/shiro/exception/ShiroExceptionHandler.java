package com.message.shiro.exception;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * shiro 异常统一处理为ShiroUnifiedException,保存系统异常一致性
 * 由于复用性不高,这里的异常信息不从error.properties取,直接写死
 *
 * @author Guoqw
 * @since 2015-07-13 11:16
 */
public class ShiroExceptionHandler {

    public static final String ERROR_CODE_KEY_TTRIBUTE_NAME = "shiro.error.code";
    public static final String ERROR_MESSAGE_KEY_ATTRIBUTE_NAME = "shiro.error.message";

    public static final ShiroUnifiedException SYSTEM_EXCEPTION = builShiroException("SHIRO_SYSTEM_0001", "系统异常");
    public static final ShiroUnifiedException SHOW_CAPTCHA_EXCEPTION = builShiroException("SHIRO_1000", "验证码为空");
    public static final ShiroUnifiedException CAPTCHA_EXCEPTION = builShiroException("SHIRO_1001", "验证码错误");
    public static final ShiroUnifiedException UNKNOWN_ACCOUNT_EXCEPTION = builShiroException("SHIRO_1002", "该账户不存在");
    public static final ShiroUnifiedException REPEAT_ACCOUNT_EXCEPTION = builShiroException("SHIRO_1003", "账户重复");
    public static final ShiroUnifiedException LOCKED_ACCOUNT_EXCEPTION = builShiroException("SHIRO_1004", "账户被锁定");
    public static final ShiroUnifiedException INCORRECT_PASSWORD_EXCEPTION = builShiroException("SHIRO_1005", "密码错误");
    public static final ShiroUnifiedException INCORRECT_REPEAT_PASSWORD_EXCEPTION = builShiroException("SHIRO_1006", "重试密码错误");
    public static final ShiroUnifiedException SESSION_TIMEOUT = builShiroException("SHIRO_SYSTEM_0002","session过期");


    /**
     * 构建统一的异常返回
     */
    public static ShiroUnifiedException buildUnifiedException(Exception e) {
        return (e instanceof ShiroUnifiedException) ? (ShiroUnifiedException) e : SYSTEM_EXCEPTION;
    }

    /**
     * 构建统一的异常
     */
    public static ShiroUnifiedException builShiroException(String code, String message) {
        return new ShiroUnifiedException(code, message);
    }

    /**
     * 构建显示到前台的错误提示信息，并对一些提示信息做模糊处理
     */
    public static String buildViewErrorMsg(Exception e) {
        if (e == null) {
            return StringUtils.EMPTY;
        }
        ShiroUnifiedException shiroUnifiedException = buildUnifiedException(e);
        String errorMsg = shiroUnifiedException.getMessage();
        String code = shiroUnifiedException.getCode();
        if (StringUtils.equals("SHIRO_1003", code) || StringUtils.equals("SHIRO_1005", code) || StringUtils.equals("SHIRO_1006", code)) {//不暴露详细登录错误信息
            errorMsg = "用户名或密码错误";
        }

        if (StringUtils.contains(code, "SYSTEM")) {//不暴露详细的系统错误
            errorMsg = "系统异常";
        }
        return errorMsg;
    }

    /**
     * 获取统一异常的code
     */
    public static String getErrorCode(Exception e) {
        if (e == null) {
            return StringUtils.EMPTY;
        }
        ShiroUnifiedException shiroUnifiedException = buildUnifiedException(e);
        return shiroUnifiedException.getCode();
    }

    /**
     * 从request中获取预先设置的错误信息
     */
    public static String getErrorMsgByRequest(HttpServletRequest request) {
        if (request == null) {
            return StringUtils.EMPTY;
        }
        Object error = request.getAttribute(ERROR_MESSAGE_KEY_ATTRIBUTE_NAME);
        if (error == null) {
            return StringUtils.EMPTY;
        }
        return (String) error;
    }

    /**
     * 从request中获取预先设置的错误code
     */
    public static String getErrorCodeByRequest(HttpServletRequest request) {
        if (request == null) {
            return StringUtils.EMPTY;
        }
        Object code = request.getAttribute(ERROR_CODE_KEY_TTRIBUTE_NAME);
        if (code == null) {
            return StringUtils.EMPTY;
        }
        return (String) code;
    }

    /**
     * 检查是否有抛出相关的异常而需要显示验证码
     *
     * @return
     */
    public static boolean needShowCaptcha(HttpServletRequest request) {
        String errorCode = getErrorCodeByRequest(request);
        return StringUtils.equals("SHIRO_1001", errorCode) || StringUtils.equals("SHIRO_1006", errorCode);
    }

}
