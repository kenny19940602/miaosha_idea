package com.imooc.kenny.redis;

/**
 * ClassName: KeyPrefix
 * Function:  redis的Key前缀
 * Date:      2019/10/17 15:43
 * @author     Kenny
 * version    V1.0
 */
public interface KeyPrefix {

    int expireSeconds();

    String getKeyPrefix();
}
