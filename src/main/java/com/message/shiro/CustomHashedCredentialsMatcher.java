package com.message.shiro;

import com.message.shiro.exception.ShiroExceptionHandler;
import com.message.util.RandomValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


/**
 * Created by zacard on 14-8-8.
 * 密码重试次数限制:
 * 输错3次，则显示验证码
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${shiro.authCodeLoginEnabled}")
    private boolean authCodeEnabled;//是否开启授权码登录

    /**
     * 密码是否匹配，在次验证扩展。
     * 这里暂时只是扩展匹配密码的时候，判断是否重复试验密码多次，以防止暴力破解
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        logger.info("开始验证密码重试次数...");
        UserToken userToken = (UserToken) token;
        if (!checkCaptcha(userToken)) {
            throw ShiroExceptionHandler.CAPTCHA_EXCEPTION;
        }
        return doMatch(userToken, info);
    }

    /**
     * 检查验证码是否正确
     *
     * @param userToken
     * @return
     */
    private boolean checkCaptcha(UserToken userToken) {
        //获取生成的验证码
        Object code = SecurityUtils.getSubject().getSession().getAttribute(RandomValidateCode.VALIDATE_CODE_NAME);
        if (StringUtils.isNotEmpty(userToken.getCaptcha())) {
            return code != null && StringUtils.equalsIgnoreCase(userToken.getCaptcha(), (String) code);
        }
        return false;
    }

    /**
     * 匹配密码
     *
     * @param userToken
     * @param info
     * @return
     */
    private boolean doMatch(UserToken userToken, AuthenticationInfo info) {
        //对表单的密码先一层md5加密加盐（私盐）
        ByteSource privateSalt = (info instanceof SaltedAuthenticationInfo) ?
                ((SaltedAuthenticationInfo) info).getCredentialsSalt() : ByteSource.Util.bytes(StringUtils.EMPTY);
        SimpleHash hash = new SimpleHash("md5", userToken.getPassword(), privateSalt);
        return StringUtils.equals(hash.toString(), (String) info.getCredentials());
    }

}
