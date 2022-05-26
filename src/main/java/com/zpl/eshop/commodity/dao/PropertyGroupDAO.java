package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.PropertyGroupDO;

import java.util.List;

/**
 * 属性分组DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/5/18 0:25
 **/

public interface PropertyGroupDAO {
    /**
     * 新增属性分组
     *
     * @param propertyGroupDO 属性分组
     * @return 新增成功
     */
    Long save(PropertyGroupDO propertyGroupDO);

    /**
     * 根据类目id查找属性分组
     *
     * @param categoryId 类目id
     * @return 属性分组
     */
    List<PropertyGroupDO> listByCategoryId(Long categoryId);
}
