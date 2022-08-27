package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsPropertyValueDAO;
import com.zpl.eshop.commodity.domain.GoodsPropertyValueDO;
import com.zpl.eshop.commodity.domain.GoodsPropertyValueDTO;
import com.zpl.eshop.commodity.service.GoodsPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性值管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 18:00
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsPropertyValueServiceImpl implements GoodsPropertyValueService {

    /**
     * 商品属性值管理DAO组件
     */
    @Autowired
    private GoodsPropertyValueDAO goodsPropertyValueDAO;

    /**
     * 新增商品属性值
     *
     * @param propertyValues 商品属性值
     */
    @Override
    public void batchSave(List<GoodsPropertyValueDTO> propertyValues) {
        propertyValues.forEach(propertyValue -> {
            try {
                goodsPropertyValueDAO.save(propertyValue.clone(GoodsPropertyValueDO.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
