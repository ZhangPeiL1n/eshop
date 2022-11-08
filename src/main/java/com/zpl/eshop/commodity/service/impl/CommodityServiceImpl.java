package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityService;
import com.zpl.eshop.commodity.service.GoodsService;
import com.zpl.eshop.commodity.service.GoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:26
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommodityServiceImpl implements CommodityService {

    /**
     * 商品Sku管理模块Service组件
     */
    @Autowired
    private GoodsSkuService goodsSkuService;

    /**
     * 商品管理Service组件
     */
    @Autowired
    private GoodsService goodsService;

    /**
     * 根据 id 查询商品 sku
     *
     * @param goodsSkuId 商品 sku id
     * @return 商品 sku dto
     */
    @Override
    public GoodsSkuDTO getGoodsSkuById(Long goodsSkuId) throws Exception {
        return goodsSkuService.getById(goodsSkuId);
    }

    /**
     * 根据id查商品
     *
     * @param goodsId 商品id
     * @return 商品
     */
    @Override
    public GoodsDTO getGoodsById(Long goodsId) throws Exception {
        return goodsService.getById(goodsId);
    }
}
