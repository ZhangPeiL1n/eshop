package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.*;
import com.zpl.eshop.commodity.domain.*;
import com.zpl.eshop.commodity.service.CategoryService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:36
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * 类目DAO组件
     */
    @Autowired
    private CategoryDAO categoryDAO;

    /**
     * 类目属性管理DAO组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO categoryPropertyRelationDAO;

    /**
     * 属性分组DAO组件
     */
    @Autowired
    private PropertyGroupDAO propertyGroupDAO;

    /**
     * 属性分组与属性管理DAO
     */
    @Autowired
    private PropertyGroupRelationshipDAO propertyGroupRelationDAO;

    /**
     * 属性管理DAO
     */
    @Autowired
    private PropertyDAO propertyDAO;

    @Autowired
    private DateProvider dateProvider;

    /**
     * 查询根类目
     *
     * @return 根类目集合
     */
    public List<CategoryDTO> listRoots() {
        try {
            List<CategoryDO> roots = categoryDAO.listRoots();
            return ObjectUtils.convertList(roots, CategoryDTO.class);
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
    public List<CategoryDTO> listChildren(Long id) {
        try {
            List<CategoryDO> children = categoryDAO.listChildren(id);
            return ObjectUtils.convertList(children, CategoryDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增类目
     *
     * @return 类目
     */
    @Override
    public Boolean save(CategoryDTO categoryDTO) {
        try {
            // 保存类目基本信息
            saveCategory(categoryDTO);
            // 保存类目与属性之间的关联关系
            saveCategoryPropertyRelations(categoryDTO);
            // 保存属性分组
            savePropertyGroup(categoryDTO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 保存类目基本信息
     *
     * @param categoryDTO 类目基本信息
     */
    private void saveCategory(CategoryDTO categoryDTO) throws Exception {

        categoryDTO.setGmtCreate(dateProvider.getCurrentTime());
        categoryDTO.setGmtModified(dateProvider.getCurrentTime());
        Long categoryId = categoryDAO.save(categoryDTO.clone(CategoryDO.class));
        categoryDTO.setId(categoryId);
    }

    /**
     * 保存类目与属性关联关系
     *
     * @param categoryDTO 类目
     * @throws Exception
     */
    private void saveCategoryPropertyRelations(CategoryDTO categoryDTO) throws Exception {
        if (categoryDTO.getPropertyRelations() == null || categoryDTO.getPropertyRelations().size() == 0) {
            return;
        }
        for (CategoryPropertyRelationshipDTO relation : categoryDTO.getPropertyRelations()) {
            relation.setCategoryId(categoryDTO.getId());
            categoryPropertyRelationDAO.save(relation.clone(CategoryPropertyRelationshipDO.class));
        }
    }

    /**
     * 保存属性分组与属性关联关系
     *
     * @param categoryDTO 类目
     * @throws Exception
     */
    private void savePropertyGroup(CategoryDTO categoryDTO) throws Exception {
        if (categoryDTO.getPropertyGroups() == null || categoryDTO.getPropertyGroups().size() == 0) {
            return;
        }
        for (PropertyGroupDTO groups : categoryDTO.getPropertyGroups()) {
            groups.setCategoryId(categoryDTO.getId());
            groups.setGmtCreate(dateProvider.getCurrentTime());
            groups.setGmtModified(dateProvider.getCurrentTime());
            Long groupId = propertyGroupDAO.save(groups.clone(PropertyGroupDO.class));
            groups.setId(groupId);
            // 保存属性分组与属性的关联关系
            savePropertyGroupRelations(groups);
        }
    }

    /**
     * 保存属性分组与属性的关联关系
     *
     * @param groups
     * @throws Exception
     */
    private void savePropertyGroupRelations(PropertyGroupDTO groups) throws Exception {
        if (groups.getRelations() == null || groups.getRelations().size() == 0) {
            return;
        }
        for (PropertyGroupRelationshipDTO relation : groups.getRelations()) {
            relation.setPropertyGroupId(groups.getId());
            relation.setGmtCreate(dateProvider.getCurrentTime());
            relation.setGmtModified(dateProvider.getCurrentTime());
            propertyGroupRelationDAO.save(relation.clone(PropertyGroupRelationshipDO.class));
        }
    }

    /**
     * 根据id查询类目
     *
     * @param id 类目id
     * @return 类目
     */
    @Override
    public CategoryDTO getById(Long id) {
        try {
            // 查询类目基本信息
            CategoryDTO category = categoryDAO.getById(id).clone(CategoryDTO.class);
            // 查询类目属性关联关系
            List<CategoryPropertyRelationshipDO> relations = categoryPropertyRelationDAO.listByCategoryId(id);
            category.setPropertyRelations(ObjectUtils.convertList(relations, CategoryPropertyRelationshipDTO.class));

            // 查询类目关联的属性
            List<PropertyDO> properties = new ArrayList<>();
            for (CategoryPropertyRelationshipDO relation : relations) {
                properties.add(propertyDAO.getPropertyById(relation.getPropertyId()));
            }
            category.setProperties(ObjectUtils.convertList(properties, PropertyDTO.class));

            // 查询类目关联的属性分组
            List<PropertyGroupDTO> propertyGroups = getPropertyByCategoryId(id);
            category.setPropertyGroups(propertyGroups);

            return category;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据类目id查询属性分组
     *
     * @param categoryId 类目id
     * @return 属性分组
     * @throws Exception
     */
    private List<PropertyGroupDTO> getPropertyByCategoryId(Long categoryId) throws Exception {

        List<PropertyGroupDTO> resultPropertyGroups = new ArrayList<>();
        // 查询类目关联的属性分组
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(categoryId);
        // 查询属性分组与属性的关联关系，以及属性分组关联的属性
        for (PropertyGroupDO propertyGroup : propertyGroups) {
            PropertyGroupDTO resultPropertyGroup = propertyGroup.clone(PropertyGroupDTO.class);
            List<PropertyGroupRelationshipDO> propertyGroupRelations = propertyGroupRelationDAO.listByPropertyGroupId(propertyGroup.getId());
            // 设置关联的关联关系
            resultPropertyGroup.setRelations(ObjectUtils.convertList(propertyGroupRelations, PropertyGroupRelationshipDTO.class));
            List<PropertyDTO> properties = new ArrayList<>();
            for (PropertyGroupRelationshipDO propertyGroupRelation : propertyGroupRelations) {
                properties.add(propertyDAO.getPropertyById(propertyGroupRelation.getPropertyId()).clone(PropertyDTO.class));
            }
            // 设置关联的属性
            resultPropertyGroup.setProperties(properties);
            resultPropertyGroups.add(resultPropertyGroup);
        }
        return resultPropertyGroups;
    }
}
