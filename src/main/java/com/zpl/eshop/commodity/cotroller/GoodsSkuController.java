package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.domain.GoodsSkuQuery;
import com.zpl.eshop.commodity.domain.GoodsSkuVO;
import com.zpl.eshop.commodity.service.GoodsSkuService;
import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * 根据商品id查询商品sku
     *
     * @param goodsId 商品id
     * @return 商品sku
     * @throws Exception
     */
    @GetMapping("/{goodsId}")
    public List<GoodsSkuVO> listByGoodsId(@PathVariable("goodsId") Long goodsId) {
        try {
            return ObjectUtils.convertList(goodsSkuService.listByGoodsId(goodsId),
                    GoodsSkuVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

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

    /**
     * 根据商品id删除sku
     *
     * @param goodsId 商品id
     */
    @DeleteMapping("/{goodsId}")
    public Boolean removeByGoodsId(@PathVariable("goodsId") Long goodsId) {
        try {
            goodsSkuService.removeByGoodsId(goodsId);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 分页查询商品sku
     *
     * @param query 查询条件
     * @return 商品sku集合
     */
    @GetMapping("/")
    public List<GoodsSkuVO> listGoodsSkusByPage(GoodsSkuQuery query) {
        try {
            return ObjectUtils.convertList(goodsSkuService.listByPage(query),
                    GoodsSkuVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }
}
