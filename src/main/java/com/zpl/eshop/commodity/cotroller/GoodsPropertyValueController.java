package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.GoodsPropertyValueDTO;
import com.zpl.eshop.commodity.domain.GoodsPropertyValueVO;
import com.zpl.eshop.commodity.service.GoodsPropertyValueService;
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
 * 商品属性值管理Controller
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 18:14
 **/
@RestController
@RequestMapping("/commodity/goods/propertyValue")
public class GoodsPropertyValueController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsPropertyValueController.class);

    /**
     * 商品属性值管理Service组件
     */
    @Autowired
    private GoodsPropertyValueService goodsPropertyValueService;

    /**
     * 新增商品属性值
     *
     * @param propertyValues 商品属性值
     */
    @PostMapping("/")
    public Boolean batchSave(@RequestBody List<GoodsPropertyValueVO> propertyValues) {
        try {
            goodsPropertyValueService.batchSave(ObjectUtils.convertList(propertyValues, GoodsPropertyValueDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
