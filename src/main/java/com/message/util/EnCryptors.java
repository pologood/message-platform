package com.message.util;


import com.message.util.cryptors.*;

public class EnCryptors {

    public static final SHA512Cryptor SHA512 = SHA512Cryptor.getInstance();
    public static final Base64Cryptor BASE64 = Base64Cryptor.getInstance();
    public static final MD5Cryptor MD5 = MD5Cryptor.getInstance();
    public static final AESCryptor AES = AESCryptor.getInstance();
    public static final DSACryptor DSA = DSACryptor.getInstance();
    public static final RSACryptor RSA = RSACryptor.getInstance();
}
