package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.GoodsDetailDTO;
import com.zpl.eshop.commodity.domain.GoodsDetailVO;
import com.zpl.eshop.commodity.service.GoodsDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品详情管理Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 17:45
 **/
@RestController
@RequestMapping("/commodity/goods/detail")
public class GoodsDetailController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsDetailController.class);

    /**
     * 商品详情管理Service组件
     */
    @Autowired
    private GoodsDetailService goodsDetailService;

    /**
     * 根据商品id查询商品详情
     *
     * @param goodsId 商品id
     * @return 商品详情
     */
    @GetMapping("/{goodsId}")
    public GoodsDetailVO getByGoodsId(@PathVariable("goodsId") Long goodsId) {
        try {
            return goodsDetailService.getByGoodsId(goodsId).clone(GoodsDetailVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new GoodsDetailVO();
        }
    }

    /**
     * 新增商品详情
     *
     * @param goodsDetail 商品详情
     * @return 商品详情id
     */
    @PostMapping("/")
    public Long save(@RequestBody GoodsDetailVO goodsDetail) throws Exception {
        try {
            return goodsDetailService.save(goodsDetail.clone(GoodsDetailDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }


    /**
     * 更新商品详情
     *
     * @param goodsDetail 商品详情
     */
    @PutMapping("/")
    Boolean update(@RequestBody GoodsDetailVO goodsDetail) throws Exception {
        try {
            goodsDetailService.update(goodsDetail.clone(GoodsDetailDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

}
