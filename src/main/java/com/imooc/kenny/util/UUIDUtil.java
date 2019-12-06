package com.imooc.kenny.util;

import java.util.UUID;

/**
 * ClassName: UUIDUtil
 * Function:  UUID工具类
 * Date:      2019/11/26 10:14
 * @author     Kenny
 * version    V1.0
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
