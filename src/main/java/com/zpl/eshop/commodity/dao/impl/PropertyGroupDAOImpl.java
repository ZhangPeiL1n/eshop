package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.PropertyGroupDAO;
import com.zpl.eshop.commodity.domain.PropertyGroupDO;
import com.zpl.eshop.commodity.mapper.PropertyGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性分组DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/18 0:26
 **/
@Repository
public class PropertyGroupDAOImpl implements PropertyGroupDAO {

    private static final Logger logger = LoggerFactory.getLogger(PropertyGroupDAOImpl.class);
    @Autowired
    private PropertyGroupMapper propertyGroupMapper;

    /**
     * 新增属性分组
     *
     * @param propertyGroupDO 属性分组
     * @return 新增成功
     * @throws Exception
     */
    @Override
    public Long save(PropertyGroupDO propertyGroupDO) throws Exception {
        propertyGroupMapper.save(propertyGroupDO);
        return propertyGroupDO.getCategoryId();
    }

    /**
     * 根据类目id查找属性分组
     *
     * @param categoryId 类目id
     * @return 属性分组
     */
    @Override
    public List<PropertyGroupDO> listByCategoryId(Long categoryId) throws Exception {
        return propertyGroupMapper.listByCategoryId(categoryId);
    }

    /**
     * 根据类目id删除属性分组
     *
     * @param categoryId 类目id
     */
    @Override
    public void removeByCategoryId(Long categoryId) throws Exception {
        propertyGroupMapper.removeByCategoryId(categoryId);
    }
}
