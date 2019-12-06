package com.imooc.kenny.service.impl;

import com.imooc.kenny.mapper.GoodsMapper;
import com.imooc.kenny.model.MiaoshaGoods;
import com.imooc.kenny.service.IGoodsService;
import com.imooc.kenny.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: GoodsServiceImpl
 * Function:  商品业务层实现类
 * Date:      2019/11/27 8:30
 * @author     Kenny
 * version    V1.0
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        int result = goodsMapper.reduceStock(g);
        return result > 0;
    }
}
