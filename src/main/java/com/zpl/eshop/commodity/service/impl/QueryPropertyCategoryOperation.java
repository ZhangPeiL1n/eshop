package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.*;
import com.zpl.eshop.commodity.domain.*;
import com.zpl.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询属性类目操作
 *
 * @author ZhangPeiL1n
 * @date 2022/9/5 22:06
 **/
@Component
@Scope("prototype")
public class QueryPropertyCategoryOperation implements CategoryOperation<Properties> {

    /**
     * 最终结果
     */
    private final Properties properties = new Properties();

    /**
     * 类目与属性关系管理DAO组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO;

    /**
     * 属性管理DAO组件
     */
    @Autowired
    private PropertyDAO propertyDAO;

    /**
     * 属性分组DAO组件
     */
    @Autowired
    private PropertyGroupDAO propertyGroupDAO;

    /**
     * 属性分组与属性的关联关系DAO组件
     */
    @Autowired
    private PropertyGroupRelationshipDAO propertyGroupRelationshipDAO;

    /**
     * 类目管理DAO
     */
    @Autowired
    private CategoryDAO categoryDAO;

    /**
     * 执行操作
     *
     * @param category 类目树节点
     * @return
     * @throws Exception
     */
    @Override
    public Properties doExecute(Category category) throws Exception {
        List<CategoryPropertyRelationshipVO> categoryPropertyRelationshipVOList = queryCategoryPropertyRelations(category);
        queryProperties(categoryPropertyRelationshipVOList);
        queryPropertyGroup(category);
        Category parentCategory = getParentCategory(category);
        if (parentCategory != null) {
            parentCategory.execute(this);
        }
        return properties;
    }

    /**
     * 获取父类目
     *
     * @param category 当前类目
     * @return 父类目
     * @throws Exception
     */
    private Category getParentCategory(Category category) throws Exception {
        CategoryDO categoryDO = categoryDAO.getById(category.getCategoryId());
        CategoryDO parentCategory = categoryDAO.getById(categoryDO.getParentId());

        return parentCategory != null ? new Category(parentCategory.getId()) : null;
    }

    /**
     * 查询类目与属性的关联关系
     *
     * @param category 类目
     * @throws Exception
     */
    private List<CategoryPropertyRelationshipVO> queryCategoryPropertyRelations(Category category) throws Exception {
        List<CategoryPropertyRelationshipDO> relationDOList = categoryPropertyRelationshipDAO.listByCategoryId(category.getCategoryId());
        List<CategoryPropertyRelationshipDTO> relationDTOList = ObjectUtils.convertList(relationDOList, CategoryPropertyRelationshipDTO.class);
        List<CategoryPropertyRelationshipVO> relationVOList = ObjectUtils.convertList(relationDTOList, CategoryPropertyRelationshipVO.class);
        properties.getPropertyRelations().addAll(relationVOList);
        return relationVOList;
    }

    /**
     * 查询类目关联的属性
     *
     * @param category 类目
     */
    private void queryProperties(List<CategoryPropertyRelationshipVO> relationshipVOList) {
        relationshipVOList.forEach(relation -> {
            try {
                PropertyDO propertyDO = propertyDAO.getPropertyById(relation.getPropertyId());
                PropertyVO propertyVO = propertyDO.clone(PropertyDTO.class).clone(PropertyVO.class);
                properties.getProperties().add(propertyVO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * 查询属性分组
     *
     * @param category 类目
     */
    private void queryPropertyGroup(Category category) throws Exception {
        List<PropertyGroupDO> propertyGroupDOList = propertyGroupDAO.listByCategoryId(category.getCategoryId());
        List<PropertyGroupDTO> propertyGroupDTOList = ObjectUtils.convertList(propertyGroupDOList, PropertyGroupDTO.class);
        List<PropertyGroupVO> propertyGroupVOList = ObjectUtils.convertList(propertyGroupDTOList, PropertyGroupVO.class);

        propertyGroupVOList.forEach(propertyGroup -> {
            try {
                propertyGroup.setRelations(queryPropertyGroupRelations(propertyGroup.getId()));
                propertyGroup.setProperties(queryPropertyForGroup(propertyGroup));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        properties.getPropertyGroups().addAll(propertyGroupVOList);
    }

    /**
     * 查询属性分组与属性的关联关系
     *
     * @param groupId 属性分组id
     * @throws Exception
     */
    private List<PropertyGroupRelationshipVO> queryPropertyGroupRelations(Long groupId) throws Exception {
        List<PropertyGroupRelationshipDO> relationDOList = propertyGroupRelationshipDAO.listByPropertyGroupId(groupId);
        List<PropertyGroupRelationshipDTO> relationDTOList = ObjectUtils.convertList(relationDOList, PropertyGroupRelationshipDTO.class);
        return ObjectUtils.convertList(relationDTOList, PropertyGroupRelationshipVO.class);
    }

    /**
     * 查询属性分组关联的属性
     *
     * @param propertyGroup 属性分组
     */
    private ArrayList<PropertyVO> queryPropertyForGroup(PropertyGroupVO propertyGroup) {
        ArrayList<PropertyVO> vos = new ArrayList<>();
        propertyGroup.getRelations().forEach(relation -> {
            try {
                PropertyVO propertyVO = propertyDAO.getPropertyById(relation.getPropertyId()).clone(PropertyDTO.class).clone(PropertyVO.class);
                vos.add(propertyVO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return vos;
    }

}
