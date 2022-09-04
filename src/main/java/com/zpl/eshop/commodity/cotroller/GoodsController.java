package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.commodity.domain.GoodsQuery;
import com.zpl.eshop.commodity.domain.GoodsVO;
import com.zpl.eshop.commodity.service.GoodsService;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品管理模块Controller
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 21:29
 **/
@RestController
@RequestMapping("/commodity/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    /**
     * 商品管理模块Service组件
     */
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询商品
     *
     * @param query 查询条件
     * @return 商品
     */
    @GetMapping("/")
    public List<GoodsVO> listByPage(@RequestBody GoodsQuery query) {
        try {
            return ObjectUtils.convertList(goodsService.listByPage(query), GoodsVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询商品
     *
     * @param id 商品id
     * @return 商品
     */
    @GetMapping("/{id}")
    public GoodsVO getById(@PathVariable("id") Long id) {
        try {
            return goodsService.getById(id).clone(GoodsVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new GoodsVO();
        }
    }

    /**
     * 新增商品
     *
     * @param goods 商品
     */
    @PostMapping("/")
    public Long save(@RequestBody GoodsVO goods) {
        try {
            Long id = goodsService.save(goods.clone(GoodsDTO.class));
            return id;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 更新商品
     *
     * @param goods 商品
     * @return 更新结果
     */
    public Boolean update(@RequestBody GoodsVO goods) {
        try {
            goodsService.update(goods.clone(GoodsDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
