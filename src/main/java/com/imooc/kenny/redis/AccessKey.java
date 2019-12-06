package com.imooc.kenny.redis;

/**
 * ClassName: AccessKey
 * Function:  TODO
 * Date:      2019/12/6 15:01
 * author     Kenny
 * version    V1.0
 */
public class AccessKey extends BasePrefix{


    protected AccessKey(String keyPrefix) {
        super(keyPrefix);
    }

    protected AccessKey(int expireSeconds, String keyPrefix) {
        super(expireSeconds, keyPrefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }
}
