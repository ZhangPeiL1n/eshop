package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.*;
import com.zpl.eshop.commodity.domain.*;
import com.zpl.eshop.commodity.service.CategoryService;
import com.zpl.eshop.common.bean.SpringApplicationContext;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:36
 **/
@Service
@Transactional(rollbackFor = Exception.class)
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
     * Spring 容器组件
     */
    @Autowired
    SpringApplicationContext context;

    /**
     * 查询根类目
     *
     * @return 根类目集合
     */
    @Override
    public List<CategoryDTO> listRoots() throws Exception {
        List<CategoryDO> roots = categoryDAO.listRoots();
        return ObjectUtils.convertList(roots, CategoryDTO.class);
    }

    /**
     * 查询子类目
     *
     * @param id 父类目 id
     * @return 子类目集合
     */
    @Override
    public List<CategoryDTO> listChildren(Long id) throws Exception {
        List<CategoryDO> children = categoryDAO.listChildren(id);
        return ObjectUtils.convertList(children, CategoryDTO.class);
    }

    /**
     * 新增类目
     *
     * @return 类目
     */
    @Override
    public Boolean save(CategoryDTO categoryDTO) throws Exception {
        // 保存类目基本信息
        saveCategory(categoryDTO);
        // 保存类目与属性之间的关联关系
        saveCategoryPropertyRelations(categoryDTO);
        // 保存属性分组
        savePropertyGroup(categoryDTO);
        return true;
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
    public CategoryDTO getById(Long id) throws Exception {
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

    /**
     * 更新类目
     *
     * @param category 类目dto
     */
    @Override
    public void update(CategoryDTO category) throws Exception {
        updateCategory(category);

        removeCategoryPropertyRelations(category);
        saveCategoryPropertyRelations(category);

        removePropertyGroupRelations(category);
        savePropertyGroup(category);
    }

    /**
     * 更新类目
     * @param category 类目
     * @throws Exception
     */
    private void updateCategory(CategoryDTO category) throws Exception{
        category.setGmtModified(dateProvider.getCurrentTime());
        categoryDAO.update(category.clone(CategoryDO.class));
    }

    /**
     * 删除类目与属性的关联关系
     * @param category 类目
     * @throws Exception
     */
    private void removeCategoryPropertyRelations(CategoryDTO category) throws Exception{
        categoryPropertyRelationDAO.removeByCategoryId(category.getId());

    }

    /**
     * 删除类目的属性分组与属性的关联关系
     *
     * @param category 类目
     */
    private void removePropertyGroupRelations(CategoryDTO category) throws Exception {
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(category.getId());
        for (PropertyGroupDO propertyGroup : propertyGroups) {
            propertyGroupRelationDAO.removeByPropertyGroupId(propertyGroup.getId());
        }
        propertyGroupDAO.removeByCategoryId(category.getId());
    }

    /**
     * 删除类目
     *
     * @param id 类目id
     * @throws Exception
     */
    @Override
    public Boolean remove(Long id) throws Exception {
        Category category = new Category(id);
        RelatedCheckCategoryOperation relatedCheckOperation = context.getBean(RelatedCheckCategoryOperation.class);
        Boolean result = category.execute(relatedCheckOperation);
        if (result) {
            return false;
        }
        RemoveCategoryOperation removeOperation = context.getBean(RemoveCategoryOperation.class);
        return category.execute(removeOperation);
    }
}
