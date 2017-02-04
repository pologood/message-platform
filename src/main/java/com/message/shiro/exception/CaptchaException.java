package com.message.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by zacard on 14-8-8.
 * 验证码错误异常类
 */
public class CaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 8688932713217807034L;

    public CaptchaException() {
        super();
    }

    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaException(String message) {
        super(message);
    }

    public CaptchaException(Throwable cause) {
        super(cause);
    }
}
