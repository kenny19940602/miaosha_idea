package com.imooc.kenny.rabbitmq;

import com.imooc.kenny.model.User;

/**
 * ClassName: MiaoshaMessage
 * Function:  TODO
 * Date:      2019/12/4 14:27
 * author     Kenny
 * version    V1.0
 */
public class MiaoshaMessage {
    private User user;
    private long goodsId;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public long getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
