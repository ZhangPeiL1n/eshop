package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.CategoryDAO;
import com.zpl.eshop.commodity.dao.CategoryPropertyRelationshipDAO;
import com.zpl.eshop.commodity.dao.PropertyGroupDAO;
import com.zpl.eshop.commodity.dao.PropertyGroupRelationshipDAO;
import com.zpl.eshop.commodity.domain.*;
import com.zpl.eshop.commodity.service.CategoryService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
