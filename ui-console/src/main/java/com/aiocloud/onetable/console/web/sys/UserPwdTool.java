package com.aiocloud.onetable.console.web.sys;

import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.web.sys.utils.AESCodeUtils;
import lombok.extern.slf4j.Slf4j;
import com.aiocloud.onetable.console.base.exception.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @description: UserPwdTool.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-16 15:47 
 */
@Slf4j
public class UserPwdTool {

    private static final RandomHelper RANDOM_HELPER = new RandomHelper(3000);

    public static void put(String randomId, String randomCode) {
        RANDOM_HELPER.put(randomId, randomCode);
    }

    public static void remove(String randomId) {
        RANDOM_HELPER.remove(randomId);
    }

    public static String doPasswordDeAesCBC(String randomId, String pwd) {

        String randomCode = RANDOM_HELPER.getRandomCode(randomId);
        if (!StringUtils.hasText(randomCode)) {
            throw new BadRequestException(ErrorCode.USER_OR_PASSWORD_ERROR);
        } else {

            try {
                String key = randomCode.substring(0, 16);
                String iv = randomCode.substring(randomCode.length() - 16);
                return AESCodeUtils.decryptCBC(pwd, key, iv);
            } catch (Exception e) {
                throw new BadRequestException(ErrorCode.PARAMETER_ERROR);
            }
        }
    }

    private static class RandomHelper {

        private static final Map<String, String> PASS_CACHE = new HashMap<>(1024);

        private final LinkedList<String> lruQueue;

        private final int threshold;

        private final Lock lock = new ReentrantLock(false);

        RandomHelper(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("RandomHelper capacity must be positive!");
            }
            this.threshold = capacity;
            this.lruQueue = new LinkedList<>();
        }

        void put(String randomId, String randomCode) {
            lock.lock();
            try {
                if (lruQueue.size() >= threshold) {
                    removeLast();
                }

                putVal(randomId, randomCode);
            } finally {
                lock.unlock();
            }
        }

        String getRandomCode(String randomId) {
            return PASS_CACHE.get(randomId);
        }

        void remove(String randomId) {
            lock.lock();
            try {
                lruQueue.remove(randomId);
                PASS_CACHE.remove(randomId);
            } finally {
                lock.unlock();
            }
        }

        private void removeLast() {
            String rId = lruQueue.removeLast();
            String rCode = PASS_CACHE.remove(rId);
            log.warn("pass cache over limited, abandoned randomId is: {}, code: {}", rId, rCode);
        }

        private void putVal(String randomId, String randomCode) {
            PASS_CACHE.put(randomId, randomCode);
            lruQueue.add(randomId);
        }

    }
}
