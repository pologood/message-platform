package com.message.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author xukai
 */
public class SignUtils {

    /**
     * 对参数进行签名
     * @return
     */
    public static String sign(Map<String, String> requestMap, SignType type,String password, String secret) {
        String signingStr = getSigningStr(requestMap);

        return sign(signingStr, type,password, secret);
    }

    public static String sign(String signingStr, SignType type, String password,  String secret){
        try {
            switch(type){
              /*  case RSA:
                     return EnCryptors.RSA.sign(signingStr, secret);
                case DSA:
                    return EnCryptors.DSA.sign(signingStr, secret);*/
                case MD5:
                default:
                    signingStr = signingStr + "&password=" + password +"&secret=" + secret;
                    return EnCryptors.MD5.md5(signingStr);
            }

        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    private static String getSigningStr(Map<String, String> requestMap){
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);
        return MapUtils.convertMapToHttpString(treeMap);
    }

    /**
     * 校验签名
     * @return
     */
    public static boolean validateSignature(String signature, Map<String, String> requestMap, SignType type, String password, String secret) throws Exception{
        String signingStr = getSigningStr(requestMap);
        return  validateSignature(signature, signingStr, type, password ,secret);
    }

    public static boolean validateSignature(String signature, String signingStr, SignType type, String password,  String secret) throws Exception{

        if(StringUtils.isEmpty(signature)){
            throw new RuntimeException();
        }

        switch(type){
            /*case RSA:
                return EnCryptors.RSA.verify(signingStr, signature, password, secret);
            case DSA:
                return EnCryptors.DSA.verify(signingStr, signature, password,secret);*/
            case MD5:
            default:
                String serverSignature = sign(signingStr, type, password ,secret);
                return StringUtils.equalsIgnoreCase(signature, serverSignature);
        }
    }

}
