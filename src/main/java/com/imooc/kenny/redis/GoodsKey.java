package com.imooc.kenny.redis;

/**
 * ClassName: GoodsKey
 * Function:  TODO
 * Date:      2019/12/2 10:02
 * author     Kenny
 * version    V1.0
 */
public class GoodsKey extends BasePrefix {

    protected GoodsKey(String keyPrefix) {
        super(keyPrefix);
    }

    protected GoodsKey(int expireSeconds, String keyPrefix) {
        super(expireSeconds, keyPrefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60, "goodsList");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "goodDetail");
    public static GoodsKey getMiaoshaGoodsStock= new GoodsKey(0, "goodStock");
}
