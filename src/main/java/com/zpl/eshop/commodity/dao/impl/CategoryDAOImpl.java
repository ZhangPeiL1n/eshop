package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.CategoryDAO;
import com.zpl.eshop.commodity.domain.CategoryDO;
import com.zpl.eshop.commodity.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类目管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:30
 **/
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询根类目
     *
     * @return 根类目集合
     */
    @Override
    public List<CategoryDO> listRoots() {
        try {
            return categoryMapper.listRoots();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 查询子类目
     *
     * @param id 父类目 id
     * @return 子类目集合
     */
    @Override
    public List<CategoryDO> listChildren(Long id) {
        try {
            return categoryMapper.listChildren(id);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增类目
     *
     * @param categoryDO 类目
     * @return 是否成功
     */
    public Long save(CategoryDO categoryDO) {
        try {
            categoryMapper.save(categoryDO);
            return categoryDO.getId();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
