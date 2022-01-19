package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.PropertyDO;
import com.zpl.eshop.commodity.domain.PropertyQuery;

import java.util.List;

/**
 * 商品属性管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/19 21:54
 **/
public interface PropertyDAO {
    /**
     * 分页查询商品属性
     *
     * @param propertyQuery 查询条件
     * @return 商品属性
     */
    List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery);

    /**
     * 新增商品属性
     *
     * @param propertyDO 商品属性DO
     * @return 新增成功返回true
     */
    Boolean saveProperty(PropertyDO propertyDO);

    /**
     * 根据id查询商品属性
     *
     * @param propertyId 属性id
     * @return 商品属性DO
     */
    PropertyDO getPropertyById(Long propertyId);

    /**
     * 更新商品属性
     *
     * @param propertyDO 商品属性DO
     * @return 更新成功返回 true
     */
    Boolean updateProperty(PropertyDO propertyDO);

}
