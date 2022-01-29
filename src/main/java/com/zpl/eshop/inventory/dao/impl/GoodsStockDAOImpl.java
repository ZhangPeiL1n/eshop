package com.zpl.eshop.inventory.dao.impl;

import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.inventory.mapper.GoodsStockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:12
 **/
@Repository
public class GoodsStockDAOImpl implements GoodsStockDAO {

    private final Logger logger = LoggerFactory.getLogger(GoodsStockDAOImpl.class);

    /**
     * 商品库存管理模块mapper组件
     */
    @Autowired
    private GoodsStockMapper goodsStockMapper;


    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    @Override
    public GoodsStockDO getGoodsStockBySkuId(Long goodsSkuId) {
        try {
            return goodsStockMapper.getGoodsStockBySkuId(goodsSkuId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    @Override
    public Boolean saveGoodsStock(GoodsStockDO goodsStockDO) {
        try {
            goodsStockMapper.saveGoodsStock(goodsStockDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新商品库存
     *
     * @param goodsStockDO 商品库存
     */
    @Override
    public Boolean updateGoodsStock(GoodsStockDO goodsStockDO) {
        try {
            goodsStockMapper.updateGoodsStock(goodsStockDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
