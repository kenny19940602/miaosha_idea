package com.imooc.kenny.service;

import com.imooc.kenny.model.MiaoshaOrder;
import com.imooc.kenny.model.OrderInfo;
import com.imooc.kenny.model.User;
import com.imooc.kenny.vo.GoodsVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: IOrderService
 * Function:  订单服务层接口
 * Date:      2019/11/27 9:46
 * @author     Kenny
 * version    V1.0
 */
public interface IOrderService {

    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId);

    @Transactional
    OrderInfo createOrder(User user, GoodsVo goods);

    OrderInfo getOrderById(long orderId);
}
