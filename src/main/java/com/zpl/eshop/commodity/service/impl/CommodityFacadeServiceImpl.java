package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * 商品中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:26
 **/
@Service
public class CommodityFacadeServiceImpl implements CommodityFacadeService {

    private final Logger logger = LoggerFactory.getLogger(CommodityFacadeServiceImpl.class);

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
            GoodsSkuDTO goodsSkuDTO = new GoodsSkuDTO();
            goodsSkuDTO.setId(goodsSkuId);
            goodsSkuDTO.setGoodsId(999L);
            goodsSkuDTO.setGoodsName("iPhonePlus");
            goodsSkuDTO.setGoodsSkuCode("MXD133221");
            goodsSkuDTO.setGoodsLength(133.00);
            goodsSkuDTO.setGoodsWidth(221.00);
            goodsSkuDTO.setGoodsHeight(333.00);
            goodsSkuDTO.setGrossWeight(123.00);
            goodsSkuDTO.setSalePrice(9999.00);
            goodsSkuDTO.setDiscountPrice(8999.00);
            goodsSkuDTO.setPurchasePrice(6789.33);
            goodsSkuDTO.setSaleProperties("机身颜色:白色,存储大小:256G");
            goodsSkuDTO.setGmtCreate(simpleDateFormat.parse("2022-02-14 11:11:11"));
            goodsSkuDTO.setGmtModified(simpleDateFormat.parse("2022-02-14 11:11:11"));
            return goodsSkuDTO;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
