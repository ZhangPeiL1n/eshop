package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.PropertyDTO;
import com.zpl.eshop.commodity.domain.PropertyQuery;
import com.zpl.eshop.commodity.service.impl.Properties;

import java.util.List;

/**
 * 商品属性管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/19 22:03
 **/
public interface PropertyService {
    /**
     * 分页查询商品属性
     *
     * @param propertyQuery 查询条件
     * @return 商品属性
     */
    List<PropertyDTO> listPropertiesByPage(PropertyQuery propertyQuery);

    /**
     * 新增商品属性
     *
     * @param propertyDTO 商品属性DO
     * @return 新增成功返回true
     */
    Boolean saveProperty(PropertyDTO propertyDTO);

    /**
     * 根据id查询商品属性
     *
     * @param propertyId 属性id
     * @return 商品属性DO
     */
    PropertyDTO getPropertyById(Long propertyId);

    /**
     * 查询类目Id对应的所有属性
     *
     * @param categoryId 类目id
     * @return
     */
    Properties getPropertiesByCategoryId(Long categoryId) throws Exception;

    /**
     * 更新商品属性
     *
     * @param propertyDTO 商品属性DO
     * @return 更新成功返回 true
     */
    Boolean updateProperty(PropertyDTO propertyDTO);
}
