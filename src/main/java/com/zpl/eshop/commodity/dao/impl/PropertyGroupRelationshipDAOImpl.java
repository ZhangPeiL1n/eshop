package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.PropertyGroupRelationshipDAO;
import com.zpl.eshop.commodity.domain.PropertyGroupRelationshipDO;
import com.zpl.eshop.commodity.mapper.PropertyGroupRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性分组与属性关系管理DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class PropertyGroupRelationshipDAOImpl implements PropertyGroupRelationshipDAO {

    private static final Logger logger = LoggerFactory.getLogger(
            PropertyGroupRelationshipDAOImpl.class);

    @Autowired
    private PropertyGroupRelationshipMapper propertyGroupRelationMapper;

    /**
     * 新增属性分组与属性关系
     *
     * @param relation 属性分组与属性关系
     */
    @Override
    public Boolean save(PropertyGroupRelationshipDO relation) throws Exception {
        propertyGroupRelationMapper.save(relation);
        return true;

    }

    /**
     * 根据属性分组id查询属性分组与属性的关联关系
     *
     * @param propertyGroupId 属性分组id
     * @return 属性分组与属性的关联关系
     */
    @Override
    public List<PropertyGroupRelationshipDO> listByPropertyGroupId(Long propertyGroupId) throws Exception {
        return propertyGroupRelationMapper.listByPropertyGroupId(propertyGroupId);
    }

    /**
     * 根据属性分组id删除属性分组和属性关联关系
     *
     * @param propertyGroupId 属性分组id
     */
    @Override
    public void removeByPropertyGroupId(Long propertyGroupId) throws Exception {
        propertyGroupRelationMapper.removeByPropertyGroupId(propertyGroupId);
    }
}
