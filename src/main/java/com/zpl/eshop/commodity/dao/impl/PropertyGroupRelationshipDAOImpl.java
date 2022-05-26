package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.PropertyGroupRelationshipDAO;
import com.zpl.eshop.commodity.domain.PropertyGroupRelationshipDO;
import com.zpl.eshop.commodity.mapper.PropertyGroupRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性分组与属性关系管理DAO组件
 *
 * @author zhonghuashishan
 */
@Repository
public class PropertyGroupRelationshipDAOImpl implements PropertyGroupRelationshipDAO {

    private static final Logger logger = LoggerFactory.getLogger(
            PropertyGroupRelationshipDAOImpl.class);

    private PropertyGroupRelationshipMapper propertyGroupRelationMapper;

    /**
     * 新增属性分组与属性关系
     *
     * @param relation 属性分组与属性关系
     */
    public Boolean save(PropertyGroupRelationshipDO relation) {
        try {
            propertyGroupRelationMapper.save(relation);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据属性分组id查询属性分组与属性的关联关系
     *
     * @param propertyGroupId 属性分组id
     * @return 属性分组与属性的关联关系
     */
    public List<PropertyGroupRelationshipDO> listByPropertyGroupId(Long propertyGroupId) {
        try {
            return propertyGroupRelationMapper.listByPropertyGroupId(propertyGroupId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

}
