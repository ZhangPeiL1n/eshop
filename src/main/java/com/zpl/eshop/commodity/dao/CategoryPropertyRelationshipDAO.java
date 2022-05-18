package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipDO;

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
    Boolean save(CategoryPropertyRelationshipDO relationshipDO);
}
