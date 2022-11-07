package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.CategoryDAO;
import com.zpl.eshop.commodity.domain.CategoryDO;
import com.zpl.eshop.commodity.mapper.CategoryMapper;
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

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询根类目
     *
     * @return 根类目集合
     * @throws Exception
     */
    @Override
    public List<CategoryDO> listRoots() throws Exception {
        return categoryMapper.listRoots();
    }

    /**
     * 查询子类目
     *
     * @param id 父类目 id
     * @return 子类目集合
     * @throws Exception
     */
    @Override
    public List<CategoryDO> listChildren(Long id) throws Exception {
        return categoryMapper.listChildren(id);
    }

    /**
     * 根据id查询类目
     *
     * @param id 类目id
     * @return 子类目集合
     * @throws Exception
     */
    @Override
    public CategoryDO getById(Long id) throws Exception {
        return categoryMapper.getById(id);
    }

    /**
     * 新增类目
     *
     * @param categoryDO 类目
     * @return 是否成功
     * @throws Exception
     */
    @Override
    public Long save(CategoryDO categoryDO) throws Exception {
        categoryMapper.save(categoryDO);
        return categoryDO.getId();
    }

    /**
     * 更新类目
     *
     * @param categoryDO 类目
     * @throws Exception
     */
    @Override
    public void update(CategoryDO categoryDO) throws Exception {
        categoryMapper.update(categoryDO);
    }

    /**
     * 删除类目
     *
     * @param id 类目id
     * @throws Exception
     */
    @Override
    public void remove(Long id) throws Exception {
        categoryMapper.remove(id);
    }
}
