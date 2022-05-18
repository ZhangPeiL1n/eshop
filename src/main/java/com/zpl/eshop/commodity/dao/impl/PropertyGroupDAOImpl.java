package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.PropertyGroupDAO;
import com.zpl.eshop.commodity.domain.PropertyGroupDO;
import com.zpl.eshop.commodity.mapper.PropertyGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 属性分组DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/18 0:26
 **/
@Repository
public class PropertyGroupDAOImpl implements PropertyGroupDAO {

    @Autowired
    private PropertyGroupMapper propertyGroupMapper;

    private static final Logger logger = LoggerFactory.getLogger(PropertyGroupDAOImpl.class);

    /**
     * 新增属性分组
     *
     * @param propertyGroupDO 属性分组
     * @return 新增成功
     */
    public Long save(PropertyGroupDO propertyGroupDO) {
        try {
            propertyGroupMapper.save(propertyGroupDO);
            return propertyGroupDO.getCategoryId();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
