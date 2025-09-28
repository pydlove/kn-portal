package com.aiocloud.ruankao.portal.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @description: WeChatUtil.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 18:31
 */
public class WeChatUtil {

    /**
     * 验证微信签名
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        // 1. 将token、timestamp、nonce排序
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);

        // 2. 拼接字符串并SHA1加密
        StringBuilder content = new StringBuilder();
        for (String s : arr) content.append(s);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            String tempSignature = bytesToHex(digest);
            return tempSignature.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}