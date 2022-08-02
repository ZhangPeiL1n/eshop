package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.CategoryDAO;
import com.zpl.eshop.commodity.domain.CategoryDO;

import java.util.List;

/**
 * 类目操作的抽象基类
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 22:54
 **/
public abstract class AbstractCategoryOperation<T> implements CategoryOperation<T> {

    /**
     * 类目管理DAO组件
     */
    protected CategoryDAO categoryDAO;

    public AbstractCategoryOperation(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * 执行类目操作
     *
     * @param category 类目树节点
     */
    @Override
    public T doExecute(Category category) throws Exception {
        doExecuteForChildren(category);
        doRealExecute(category);
        return getResult();
    }

    /**
     * 递归对子类目执行当前操作
     *
     * @param category
     * @throws Exception
     */
    private void doExecuteForChildren(Category category) throws Exception {
        List<CategoryDO> children = categoryDAO.listChildren(category.getCategoryId());
        if (children != null && children.size() > 0) {
            for (CategoryDO child : children) {
                Category childCategory = new Category(child.getId());
                childCategory.execute(this);
            }
        }
    }

    /**
     * 执行实际的操作
     *
     * @param category
     * @throws Exception
     */
    abstract protected void doRealExecute(Category category) throws Exception;

    /**
     * 获取操作结果
     *
     * @return
     * @throws Exception
     */
    abstract protected T getResult() throws Exception;
}
