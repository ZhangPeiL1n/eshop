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
     * @throws Exception
     */
    List<PropertyDTO> listPropertiesByPage(PropertyQuery propertyQuery) throws Exception;

    /**
     * 新增商品属性
     *
     * @param propertyDTO 商品属性DO
     * @return 新增成功返回true
     * @throws Exception
     */
    Boolean saveProperty(PropertyDTO propertyDTO) throws Exception;

    /**
     * 根据id查询商品属性
     *
     * @param propertyId 属性id
     * @return 商品属性DO
     * @throws Exception
     */
    PropertyDTO getPropertyById(Long propertyId) throws Exception;

    /**
     * 查询类目Id对应的所有属性
     *
     * @param categoryId 类目id
     * @return 属性
     * @throws Exception
     */
    Properties getPropertiesByCategoryId(Long categoryId) throws Exception;

    /**
     * 更新商品属性
     *
     * @param propertyDTO 商品属性DO
     * @return 更新成功返回 true
     * @throws Exception
     */
    Boolean updateProperty(PropertyDTO propertyDTO) throws Exception;
}
