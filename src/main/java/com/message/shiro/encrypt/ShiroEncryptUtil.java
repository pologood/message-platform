package com.message.shiro.encrypt;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;

/**
 * shiro加密解密工具类
 *
 * @author hpj
 * @since 2015-07-14 15:55
 */
public class ShiroEncryptUtil {

    protected final static Logger logger = LoggerFactory.getLogger(ShiroEncryptUtil.class);

    private static AesCipherService aesCipherService = new AesCipherService();
    private static final Key key = aesCipherService.generateNewKey(128);//密钥

    //加密
    public static String encrypt(String text) {
        String result = null;
        try {
            result = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        } catch (Exception e) {
            logger.error("encrypt fail",e);
        }
        return result;
    }

    //解密
    public static String decrypt(String encryotText) {
        String result = null;
        try {
            result = new String(aesCipherService.decrypt(Hex.decode(encryotText), key.getEncoded()).getBytes());
        } catch (Exception e) {
            logger.error("decrypt fail", e);
        }
        return result;
    }

}
