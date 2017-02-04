package com.message.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by zacard on 14-8-7.
 * 用户令牌类:包括用户名、密码、验证码
 */
public class UserToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 8761784396052101352L;

    private String captcha;//验证码

    private String code;//微信请求附带的授权码

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserToken() {
        super();
    }

    public UserToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public UserToken(String username, String password, boolean rememberMe, String host, String code) {
        super(username, password, rememberMe, host);
        this.code = code;
    }
}
