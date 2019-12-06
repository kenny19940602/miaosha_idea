package com.imooc.kenny.controller;

import com.imooc.kenny.model.OrderInfo;
import com.imooc.kenny.model.User;
import com.imooc.kenny.redis.RedisService;
import com.imooc.kenny.result.CodeMsg;
import com.imooc.kenny.result.Result;
import com.imooc.kenny.service.IGoodsService;
import com.imooc.kenny.service.IOrderService;
import com.imooc.kenny.service.IUserService;
import com.imooc.kenny.vo.GoodsVo;
import com.imooc.kenny.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: OrderController
 * Function:  订单控制器层
 * Date:      2019/12/2 10:38
 * @author     Kenny
 * version    V1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, User user, @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }


}
