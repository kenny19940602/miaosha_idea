package com.imooc.kenny.service;

import com.imooc.kenny.vo.GoodsVo;

import java.util.List;

/**
 * ClassName: IGoodsService
 * Function:  商品业务层接口
 * Date:      2019/11/27 8:29
 * @author     Kenny
 * version    V1.0
 */
public interface IGoodsService {

    List<GoodsVo> listGoodsVo();

    GoodsVo getGoodsVoByGoodsId(long goodsId);

    boolean reduceStock(GoodsVo goods);
}
