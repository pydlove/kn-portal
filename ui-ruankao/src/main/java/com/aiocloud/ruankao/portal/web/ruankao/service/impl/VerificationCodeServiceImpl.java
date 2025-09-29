package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.ruankao.portal.web.ruankao.service.VerificationCodeService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.SendCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @description: VerificationCodeServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:57
 */
@Slf4j
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final Map<String, CodeEntry> cacheMap;
    private static final int CODE_LENGTH = 6;
    private static final long CODE_EXPIRE_TIME = 5 * 60 * 1000;

    public VerificationCodeServiceImpl() {
        this.cacheMap = new HashMap<>();
    }

    private void cleanExpiredCodes() {
        long currentTime = System.currentTimeMillis();
        cacheMap.entrySet().removeIf(entry -> entry.getValue().getExpireTime() < currentTime);
    }

    @Override
    public String generateCode(String key) {

        // 生成6位随机数字验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }

        String verificationCode = code.toString();
        // 存入Map，记录过期时间
        cacheMap.put("verification:code:" + key, new CodeEntry(verificationCode, System.currentTimeMillis() + CODE_EXPIRE_TIME));

        log.info("key: {}, generate verification code：{}", key, verificationCode);

        return verificationCode;
    }

    @Override
    public boolean validateCode(String key, String code) {

        // 清理过期项
        cleanExpiredCodes();

        CodeEntry entry = cacheMap.get("verification:code:" + key);
        if (entry == null) {
            return false;
        }

        boolean isValid = entry.getCode().equals(code);
        // 验证成功后删除验证码
        if (isValid) {
            removeCode(key);
        }

        return isValid;
    }

    @Override
    public void removeCode(String key) {
        cacheMap.remove("verification:code:" + key);
    }

    @Override
    public String getVerificationKey() {
        Random random = new Random();

        // 生成4位数字key (1000-9999)
        int key;
        String keyStr;

        do {
            // 生成1000-9999之间的四位数字
            key = 1000 + random.nextInt(9000);
            keyStr = String.valueOf(key);

            // 检查是否有重复数字
            if (hasDuplicateDigits(keyStr)) {
                continue; // 如果有重复数字则重新生成
            }

            // 检查缓存中是否已存在该key
            if (!cacheMap.containsKey("verification:code:" + keyStr)) {
                break; // 没有重复数字且不在缓存中则跳出循环
            }

            // 如果key已存在，则添加随机延迟避免冲突
            ThreadUtil.safeSleep(1);
        } while (true);

        generateCode(keyStr);

        return keyStr;
    }

    @Override
    public String getVerificationCode(SendCodeVO sendCodeVO) {

        String key = sendCodeVO.getKey();
        log.info("key: {}, start get verification code", key);

        if (StrUtil.isEmpty(key)) {
            return null;
        }

        CodeEntry entry = cacheMap.get("verification:code:" + key);
        if (entry == null) {
            return null;
        }

        log.info("key: {}, end get verification code: {}", key, entry.getCode());

        return entry.getCode();
    }

    /**
     * 检查字符串中是否有重复数字
     * @param str 待检查的字符串
     * @return true表示有重复数字，false表示无重复数字
     */
    private boolean hasDuplicateDigits(String str) {
        boolean[] used = new boolean[10]; // 记录0-9数字是否已使用
        for (char c : str.toCharArray()) {
            int digit = c - '0';
            if (used[digit]) {
                return true; // 发现重复数字
            }
            used[digit] = true;
        }
        return false;
    }
}
