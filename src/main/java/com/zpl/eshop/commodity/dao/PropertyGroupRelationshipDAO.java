package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.PropertyGroupRelationshipDO;

import java.util.List;

/**
 * 属性分组与属性关系管理DAO组件接口
 *
 * @author zhangpeilin
 */
public interface PropertyGroupRelationshipDAO {

    /**
     * 新增属性分组与属性关系
     *
     * @param relation 属性分组与属性关系
     * @return 新增结果
     * @throws Exception
     */
    Boolean save(PropertyGroupRelationshipDO relation) throws Exception;

    /**
     * 根据属性分组id查询属性分组与属性的关联关系
     *
     * @param propertyGroupId 属性分组id
     * @return 属性分组与属性的关联关系
     * @throws Exception
     */
    List<PropertyGroupRelationshipDO> listByPropertyGroupId(Long propertyGroupId) throws Exception;

    /**
     * 根据属性分组id删除属性分组和属性关联关系
     *
     * @param propertyGroupId 属性分组id
     * @throws Exception
     */
    void removeByPropertyGroupId(Long propertyGroupId) throws Exception;

}
