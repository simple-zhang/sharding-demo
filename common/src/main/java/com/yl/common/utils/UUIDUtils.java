package com.yl.common.utils;

import java.util.UUID;

/**
 * @author baizs
 * @date 2022/4/12
 */
public class UUIDUtils {

    private UUIDUtils() {
        throw new IllegalStateException("UUIDUtils class");
    }

    public static String uuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
