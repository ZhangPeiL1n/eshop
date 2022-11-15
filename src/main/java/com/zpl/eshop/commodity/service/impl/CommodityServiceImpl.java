package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

/**
 * 商品中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:26
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommodityServiceImpl implements CommodityService {

    private final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    /**
     * 根据 id 查询商品 sku
     *
     * @param goodsSkuId 商品 sku id
     * @return 商品 sku dto
     */
    @Override
    public GoodsSkuDTO getGoodsSkuById(Long goodsSkuId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            GoodsSkuDTO goodsSku = new GoodsSkuDTO();
            goodsSku.setId(goodsSkuId);
            goodsSku.setGoodsId(999L);
            goodsSku.setGoodsName("iPhonePlus");
            goodsSku.setGoodsSkuCode("MXD133221");
            goodsSku.setGoodsLength(133.00);
            goodsSku.setGoodsWidth(221.00);
            goodsSku.setGoodsHeight(333.00);
            goodsSku.setGrossWeight(123.00);
            goodsSku.setSalePrice(9999.00);
            goodsSku.setDiscountPrice(8999.00);
            goodsSku.setPurchasePrice(6789.33);
            goodsSku.setSaleProperties("机身颜色:白色,存储大小:256G");
            goodsSku.setGmtCreate(simpleDateFormat.parse("2022-02-14 11:11:11"));
            goodsSku.setGmtModified(simpleDateFormat.parse("2022-02-14 11:11:11"));
            return goodsSku;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查商品
     *
     * @param goodsId 商品id
     * @return 商品
     */
    @Override
    public GoodsDTO getGoodsById(Long goodsId) {
        return null;
    }
}
