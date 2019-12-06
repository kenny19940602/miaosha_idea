package com.imooc.kenny.redis;

/**
 * ClassName: UserKey
 * Function:  用户key
 * Date:      2019/10/17 15:52
 * @author     Kenny
 * version    V1.0
 */
public class UserKey extends BasePrefix{

    public static final int TOKEN_EXPIRE = 3600*24*2;
    private UserKey(String keyPrefix) {
        super(keyPrefix);
    }

    private UserKey(int expire, String keyPrfix) {
        super(expire, keyPrfix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
    public static UserKey getToken = new UserKey(TOKEN_EXPIRE, "token");
}
