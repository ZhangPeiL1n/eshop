package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.CategoryDO;

import java.util.List;

/**
 * 类目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:29
 **/

public interface CategoryDAO {

    /**
     * 查询根类目
     *
     * @return 根类目集合
     */
    List<CategoryDO> listRoots();

    /**
     * 查询子类目
     *
     * @param id 父类目 id
     * @return 子类目集合
     */
    List<CategoryDO> listChildren(Long id);

    /**
     * 新增类目
     *
     * @param categoryDO 类目
     * @return 是否成功
     */
    Long save(CategoryDO categoryDO);
}
