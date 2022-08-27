package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.CategoryPropertyRelationshipDAO;
import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipDO;
import com.zpl.eshop.commodity.mapper.CategoryPropertyRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类目属性管理DAO组件
 *
 * @author zhonghuashishan
 */
@Repository
public class CategoryPropertyRelationshipDAOImpl
        implements CategoryPropertyRelationshipDAO {

    private static final Logger logger = LoggerFactory.getLogger(
            CategoryPropertyRelationshipDAOImpl.class);

    @Autowired
    private CategoryPropertyRelationshipMapper categoryPropertyRelationMapper;

    /**
     * 新增类目属性关系
     *
     * @param relation 类目属性关系
     */
    @Override
    public Boolean save(CategoryPropertyRelationshipDO relation) throws Exception {
        categoryPropertyRelationMapper.save(relation);
        return true;
    }

    /**
     * 根据类目id查询类目与属性的关联关系
     *
     * @param categoryId 类目id
     * @return 类目与属性的关联关系
     */
    @Override
    public List<CategoryPropertyRelationshipDO> listByCategoryId(Long categoryId) throws Exception {
        return categoryPropertyRelationMapper.listByCategoryId(categoryId);
    }

    /**
     * 根据id查询类目与属性的关联关系
     *
     * @param id 类目属性关联关系id
     * @return 类目属性关联关系DO
     */
    @Override
    public CategoryPropertyRelationshipDO getById(Long id) {
        return categoryPropertyRelationMapper.getById(id);
    }

    /**
     * 根据类目id删除
     *
     * @param categoryId 类目id
     */
    @Override
    public void removeByCategoryId(Long categoryId) throws Exception {
        categoryPropertyRelationMapper.removeByCategoryId(categoryId);
    }
}
