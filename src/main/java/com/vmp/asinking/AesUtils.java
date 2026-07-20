package com.vmp.asinking;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AesUtils {

    private static final Logger logger = LoggerFactory.getLogger(AesUtils.class);

    private static final String ECB_MODE = "AES/ECB/PKCS5PADDING";

    /**
     * <p>
     * aes加密采用ecb模式，填充方式为pkcs5padding
     * 其中cbc模式需要有向量iv
     * </p>
     *
     * @param password 密码
     * @param appKey   生成的16位appKey
     * @return aes加密的密码
     */
    public static String encryptEcb(String password, String appKey) {
        try {
            SecretKeySpec spec = new SecretKeySpec(appKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(ECB_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            logger.error("encrypt ecb error.", ex);
        }
        return "";
    }

    /**
     * aes ecb解密
     *
     * @param encrypted aes ecb加密后的密码
     * @param appKey    生成的16位appKey
     * @return password
     */
    public static String decryptEcb(String encrypted, String appKey) {
        try {
            SecretKeySpec spec = new SecretKeySpec(appKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(ECB_MODE);
            cipher.init(Cipher.DECRYPT_MODE, spec);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original);
        } catch (Exception ex) {
            logger.error("decrypt ecb error.", ex);
        }
        return "";
    }
}
