package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.CategoryDO;
import org.apache.ibatis.annotations.Param;

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
     * @throws Exception
     */
    List<CategoryDO> listRoots() throws Exception;

    /**
     * 查询子类目
     *
     * @param id 父类目 id
     * @return 子类目集合
     * @throws Exception
     */
    List<CategoryDO> listChildren(Long id) throws Exception;

    /**
     * 根据id查询类目
     *
     * @param id 类目id
     * @return 子类目集合
     * @throws Exception
     */
    CategoryDO getById(@Param("id") Long id) throws Exception;

    /**
     * 新增类目
     *
     * @param categoryDO 类目
     * @return 是否成功
     * @throws Exception
     */
    Long save(CategoryDO categoryDO) throws Exception;

    /**
     * 更新类目
     *
     * @param categoryDO 类目
     * @throws Exception
     */
    void update(CategoryDO categoryDO) throws Exception;

    /**
     * 删除类目
     *
     * @param id 类目id
     * @throws Exception
     */
    void remove(Long id) throws Exception;
}
