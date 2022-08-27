package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipDO;

import java.util.List;

/**
 * 类目属性关系DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/5/18 0:17
 **/
public interface CategoryPropertyRelationshipDAO {

    /**
     * 新增类目属性关系
     *
     * @param relationshipDO 类目属性关系
     * @return 新增成功
     */
    Boolean save(CategoryPropertyRelationshipDO relationshipDO) throws Exception;

    /**
     * 根据类目id查询类目与属性的关联关系
     *
     * @param categoryId 类目id
     * @return 类目属性关联关系DO
     */
    List<CategoryPropertyRelationshipDO> listByCategoryId(Long categoryId) throws Exception;

    /**
     * 根据id查询类目与属性的关联关系
     *
     * @param id 类目属性关联关系id
     * @return 类目属性关联关系DO
     */
    CategoryPropertyRelationshipDO getById(Long id);

    /**
     * 根据类目id删除
     *
     * @param categoryId 类目id
     */
    void removeByCategoryId(Long categoryId) throws Exception;

}
