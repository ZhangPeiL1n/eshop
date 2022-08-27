package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.domain.GoodsSkuVO;
import com.zpl.eshop.commodity.service.GoodsSkuService;
import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品SKU管理 controller 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 19:13
 **/
@RestController
@RequestMapping("/commodity/goods/sku")
public class GoodsSkuController {

    private static Logger logger = LoggerFactory.getLogger(GoodsSkuController.class);

    /**
     * 商品sku管理Service组件
     */
    @Autowired
    private GoodsSkuService goodsSkuService;

    /**
     * 批量新增
     *
     * @return 操作结果
     */
    @PostMapping("/")
    public Boolean batchSave(@RequestBody List<GoodsSkuVO> goodsSkus) {
        try {
            goodsSkuService.batchSave(ObjectUtils.convertList(goodsSkus, GoodsSkuDTO.class, CloneDirection.FORWARD));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
