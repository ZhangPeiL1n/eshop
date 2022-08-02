package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.CategoryDAO;
import com.zpl.eshop.commodity.dao.CategoryPropertyRelationshipDAO;
import com.zpl.eshop.commodity.dao.PropertyGroupDAO;
import com.zpl.eshop.commodity.dao.PropertyGroupRelationshipDAO;
import com.zpl.eshop.commodity.domain.PropertyGroupDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类目删除操作
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 23:28
 **/
@Component
@Scope("prototype")
public class CategoryRemoveOperation extends AbstractCategoryOperation<Boolean> {

    /**
     * 类目与属性关系管理DAO组件
     */
    private final CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO;

    /**
     * 属性分组管理DAO组件
     */
    private final PropertyGroupDAO propertyGroupDAO;

    /**
     * 属性分组与属性关系管理DAO组件
     */
    private final PropertyGroupRelationshipDAO propertyGroupRelationshipDAO;


    /**
     * 构造函数
     *
     * @param categoryDAO                     类目管理DAO组件
     * @param categoryPropertyRelationshipDAO 类目与属性关系管理DAO组件
     * @param propertyGroupDAO                属性分组管理DAO组件
     * @param propertyGroupRelationshipDAO    属性分组与属性关联关系DAO组件
     * @param categoryDAO                     类目管理DAO组件
     */
    @Autowired
    public CategoryRemoveOperation(CategoryDAO categoryDAO, CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO, PropertyGroupDAO propertyGroupDAO, PropertyGroupRelationshipDAO propertyGroupRelationshipDAO) {
        super(categoryDAO);
        this.categoryPropertyRelationshipDAO = categoryPropertyRelationshipDAO;
        this.propertyGroupDAO = propertyGroupDAO;
        this.propertyGroupRelationshipDAO = propertyGroupRelationshipDAO;

    }

    @Override
    protected void doRealExecute(Category category) throws Exception {
        removePropertyRelation(category);
        removePropertyGroup(category);
        removeCategory(category);
    }

    /**
     * 获取操作执行结果
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Boolean getResult() throws Exception {
        return true;
    }

    /**
     * 删除类目与属性的关联关系
     *
     * @param category 类目
     */
    private void removePropertyRelation(Category category) throws Exception {
        categoryPropertyRelationshipDAO.removeByCategoryId(category.getCategoryId());
    }

    /**
     * 删除类目的属性分组关联关系
     *
     * @param category 类目
     * @throws Exception
     */
    private void removePropertyGroup(Category category) throws Exception {
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(category.getCategoryId());
        for (PropertyGroupDO propertyGroup : propertyGroups) {
            propertyGroupRelationshipDAO.removeByPropertyGroupId(propertyGroup.getId());
        }
        propertyGroupDAO.removeByCategoryId(category.getCategoryId());
    }

    private void removeCategory(Category category) throws Exception {
        categoryDAO.remove(category.getCategoryId());
    }
}
