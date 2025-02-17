/**
 * 2009-10-5
 */
package com.aiocloud.onetable.console.web.login.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES安全编码组件
 */
public abstract class AESCodeUtils {

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * Java 6支持PKCS5Padding填充方式
     * Bouncy Castle支持PKCS7Padding填充方式
     */
    public static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    public static final String charsetName = "UTF-8";


    /**
     * aes cbc模式加密
     *
     * @param value 需要加密的明文
     * @param key   密钥 128位 16个字节
     * @param IV    初始向量
     * @return
     */
    public static String encryptCBC(String value, String key, String IV) throws Exception {
        byte[] content = new byte[0];
        content = value.getBytes(charsetName);

        //获取key
        byte[] keyBytes = key.getBytes(charsetName);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, KEY_ALGORITHM);

        //获取iv
        byte[] ivBytes = IV.getBytes(charsetName);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] data = cipher.doFinal(content);
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * aes cbc模式解密
     *
     * @param value 需要加密的明文
     * @param key   密钥 128位 16个字节
     * @param IV    初始向量
     * @return
     */
    public static String decryptCBC(String value, String key, String IV) throws Exception {
        //base64解码
        byte[] decode = Base64.getDecoder().decode(value);

        //获取key
        byte[] keyBytes = key.getBytes(charsetName);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, KEY_ALGORITHM);

        //获取iv
        byte[] ivBytes = IV.getBytes(charsetName);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] byteDES = cipher.doFinal(decode);
        return new String(byteDES, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String s = encryptCBC("123456", "LTE0OTYwMzA2OTYx", "LTE0OTYwMzA2OTYx");
        System.out.println(s);
        String s1 = decryptCBC(s, "LTE0OTYwMzA2OTYx", "LTE0OTYwMzA2OTYx");
        System.out.println(s1);
    }
}
