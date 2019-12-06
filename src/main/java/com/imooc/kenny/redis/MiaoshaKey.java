package com.imooc.kenny.redis;

/**
 * ClassName: MiaoshaKey
 * Function:  TODO
 * Date:      2019/12/4 15:20
 * author     Kenny
 * version    V1.0
 */
public class MiaoshaKey extends BasePrefix{
    private MiaoshaKey(String prefix) {
        super(prefix);
    }

    public MiaoshaKey(int expireSeconds, String keyPrefix) {
        super(expireSeconds, keyPrefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");
    public static MiaoshaKey getMiaoshaPaht = new MiaoshaKey(60,"mp");
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey(300,"vc");

}
