package com.imooc.kenny.vo;

import com.imooc.kenny.model.OrderInfo;

/**
 * ClassName: OrderDetailVo
 * Function:  订单VO类
 * Date:      2019/12/2 11:23
 * @author     Kenny
 * version    V1.0
 */
public class OrderDetailVo {

    private GoodsVo goods;
    private OrderInfo order;
    public GoodsVo getGoods() {
        return goods;
    }
    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }
    public OrderInfo getOrder() {
        return order;
    }
    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
