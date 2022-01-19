package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.PropertyDAO;
import com.zpl.eshop.commodity.domain.PropertyDO;
import com.zpl.eshop.commodity.domain.PropertyQuery;
import com.zpl.eshop.commodity.mapper.PropertyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/19 21:56
 **/
@Repository
public class PropertyDAOImpl implements PropertyDAO {

    private final Logger logger = LoggerFactory.getLogger(PropertyDAOImpl.class);
    /**
     * 商品属性管理模块Mapper组件
     */
    @Autowired
    private PropertyMapper propertyMapper;

    /**
     * 分页查询商品属性
     *
     * @param propertyQuery 查询条件
     * @return 商品属性
     */
    @Override
    public List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery) {
        try {
            return propertyMapper.listPropertiesByPage(propertyQuery);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增商品属性
     *
     * @param propertyDO 商品属性DO
     */
    @Override
    public Boolean saveProperty(PropertyDO propertyDO) {
        try {
            propertyMapper.saveProperty(propertyDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 根据id查询商品属性
     *
     * @param propertyId 属性id
     * @return 商品属性DO
     */
    @Override
    public PropertyDO getPropertyById(Long propertyId) {
        try {
            return propertyMapper.getPropertyById(propertyId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 更新商品属性
     *
     * @param propertyDO 商品属性DO
     */
    @Override
    public Boolean updateProperty(PropertyDO propertyDO) {
        try {
            propertyMapper.updateProperty(propertyDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
