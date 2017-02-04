package com.message.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * shiro 统一异常
 *
 * @author Guoqw
 * @since 2015-07-13 14:08
 */
public class ShiroUnifiedException extends AuthenticationException {

    private static final long serialVersionUID = 1103074595806939165L;

    public static final String DEFAULT_FAULT_CODE = "SHIRO_SYSTEM_0001";

    private String code;
    private String message;


    public ShiroUnifiedException(String message) {
        this(DEFAULT_FAULT_CODE, message);
    }

    public ShiroUnifiedException(String code, String message) {
        this(code, message, new Throwable());
    }

    public ShiroUnifiedException(String code, String message,String internalMessage) {
        this(code, message, internalMessage, null);
    }

    public ShiroUnifiedException(String code, String message, Throwable throwable) {
        this(code, message, throwable.getMessage(), throwable);
    }

    public ShiroUnifiedException(String code, String message, String internalMessage, Throwable throwable) {
        super("[" + code + "] - [" + message +"]" + internalMessage, throwable);
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
