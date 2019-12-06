package com.imooc.kenny.redis;

/**
 * ClassName: OrderKey
 * Function:  TODO
 * Date:      2019/10/17 15:53
 * author     Kenny
 * version    V1.0
 */
public class OrderKey extends BasePrefix {
    private OrderKey(int expireSeconds, String keyPrefix) {
        super(expireSeconds, keyPrefix);
    }

    private OrderKey(String keyPrefix) {
        super(0, keyPrefix);
    }
    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("miaoshaOrder");
}
