package com.imooc.kenny.model;

import java.io.Serializable;

/**
 * ClassName: MiaoshaOrder
 * Function:  秒杀订单实体类
 * Date:      2019/11/27 8:24
 * @author     Kenny
 * version    V1.0
 */
public class MiaoshaOrder implements Serializable {

    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "MiaoshaOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                '}';
    }
}
