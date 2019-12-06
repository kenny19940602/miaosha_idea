package com.imooc.kenny.service.impl;

import com.imooc.kenny.model.MiaoshaOrder;
import com.imooc.kenny.model.OrderInfo;
import com.imooc.kenny.model.User;
import com.imooc.kenny.redis.MiaoshaKey;
import com.imooc.kenny.redis.RedisService;
import com.imooc.kenny.service.IGoodsService;
import com.imooc.kenny.service.IMiaoshaService;
import com.imooc.kenny.service.IOrderService;
import com.imooc.kenny.util.MD5Util;
import com.imooc.kenny.util.UUIDUtil;
import com.imooc.kenny.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * ClassName: MiaoshaServiceImpl
 * Function:  秒杀业务层实现类
 * Date:      2019/11/27 9:54
 * @author     Kenny
 * version    V1.0
 */
@Service
public class MiaoshaServiceImpl implements IMiaoshaService {

    private static char[] ops = new char[]{'+', '-', '*'};

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public OrderInfo miaosha(User user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        boolean result = goodsService.reduceStock(goods);
        if (result) {
            //order_info maiosha_order
            return orderService.createOrder(user, goods);
        } else {
            setGooodsOver(goods.getId());
            return null;
        }
    }

    @Override
    public long getMiaoShaResult(Long id, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(id, goodsId);
        if (order != null) {//秒杀成功
            return order.getOrderId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return  0;
            }
        }
    }

    @Override
    public boolean checkVerifyCode(User user, long goodsId, int verifyCode) {
        if (user == null || goodsId <= 0) {
            return false;
        }
        Integer codeOld = redisService.get(MiaoshaKey.getMiaoshaVerifyCode, user.getId() + "_" + goodsId, Integer.class);
        if (codeOld == null || codeOld - verifyCode != 0) {
            return false;
        }
        redisService.delete(MiaoshaKey.getMiaoshaVerifyCode, user.getId() + "_" + goodsId);
        return true;
    }

    @Override
    public String createMiaoshaPath(User user, long goodsId) {
        if (user == null || goodsId <= 0) {
            return null;
        }
        String string = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisService.set(MiaoshaKey.getMiaoshaPaht, user.getId() + "_" + goodsId, string);
        return string;
    }

    @Override
    public boolean checkPath(User user, long goodsId, String path) {
        if (user == null || path == null) {
            return false;
        }
        String pathOld = redisService.get(MiaoshaKey.getMiaoshaPaht, user.getId() + "_" + goodsId, String.class);
        return path.equals(pathOld);
    }

    @Override
    public BufferedImage createVerifyCode(User user, long goodsId) {
        if (user == null || goodsId <= 0) {
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        //set the background color
        graphics.setColor(new Color(0xDCDCDC));
        graphics.fillRect(0, 0, width - 1, height - 1);
        //create a random instance to generate the codes
        Random random = new Random();
        //make some confusion
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            graphics.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(random);
        graphics.setColor(new Color(0, 100, 0));
        graphics.setFont(new Font("Candara", Font.BOLD, 24));
        graphics.drawString(verifyCode, 8, 24);
        graphics.dispose();
        //把验证码存入到redis中
        int rnd = calc(verifyCode);
        redisService.set(MiaoshaKey.getMiaoshaVerifyCode, user.getId() + "_" + goodsId, rnd);
        //输出图片
        return image;
    }

    private int calc(String verifyCode) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (int) engine.eval(verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * + - *
     * @param random
     * @return
     */
    private String generateVerifyCode(Random random) {
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        char ops1 = ops[random.nextInt(3)];
        char ops2 = ops[random.nextInt(3)];
        String exp = "" + num1 + ops1 + num2 + ops2 + num3;
        return exp;
    }


    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(MiaoshaKey.isGoodsOver, "" + goodsId);
    }
    private void setGooodsOver(long goodsId) {
        redisService.exists(MiaoshaKey.isGoodsOver, "" + goodsId);
    }
}
