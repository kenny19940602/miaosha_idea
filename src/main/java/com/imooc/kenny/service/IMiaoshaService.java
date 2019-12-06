package com.imooc.kenny.service;

import com.imooc.kenny.model.OrderInfo;
import com.imooc.kenny.model.User;
import com.imooc.kenny.vo.GoodsVo;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;

/**
 * ClassName: IMiaoshaService
 * Function:  秒杀业务层接口
 * Date:      2019/11/27 9:53
 * @author     Kenny
 * version    V1.0
 */
public interface IMiaoshaService {

    @Transactional
    OrderInfo miaosha(User user, GoodsVo goods);

    long getMiaoShaResult(Long id, long goodsId);

    boolean checkVerifyCode(User user, long goodsId, int verifyCode);

    String createMiaoshaPath(User user, long goodsId);

    boolean checkPath(User user, long goodsId, String path);

    BufferedImage createVerifyCode(User user, long goodsId);
}
