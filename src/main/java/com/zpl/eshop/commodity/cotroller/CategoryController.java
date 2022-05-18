package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.*;
import com.zpl.eshop.commodity.service.CategoryService;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 类目管理模块Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:41
 **/
@RestController
@RequestMapping(("/commodity/category"))
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * 类目管理 service 组件
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询根类目
     *
     * @return 根类目集合
     */
    @GetMapping("/root")
    public List<CategoryVO> listRoots() {
        try {
            List<CategoryDTO> categories = categoryService.listRoots();
            return ObjectUtils.convertList(categories, CategoryVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    @GetMapping("/children/{id}")
    public List<CategoryVO> listChildren(@PathVariable("id") Long id) {
        try {
            List<CategoryDTO> categories = categoryService.listChildren(id);
            return ObjectUtils.convertList(categories, CategoryVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 新增类目
     *
     * @param categoryVO 类目
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean save(@RequestBody CategoryVO categoryVO) {
        try {
            // 转换类目基本信息
            CategoryDTO targetCategory = categoryVO.clone(CategoryDTO.class);
            // 转换类目与属性的关联关系
            List<CategoryPropertyRelationshipDTO> targetRelations = ObjectUtils.convertList(categoryVO.getPropertyRelations(), CategoryPropertyRelationshipDTO.class);
            // 转换属性分组与属性的关联关系
            List<PropertyGroupDTO> targetGroups = new ArrayList<>(categoryVO.getPropertyGroups().size());
            for (PropertyGroupVO group : categoryVO.getPropertyGroups()) {
                PropertyGroupDTO targetGroupDTO = group.clone(PropertyGroupDTO.class);
                targetGroupDTO.setRelations(ObjectUtils.convertList(group.getRelations(), PropertyGroupRelationshipDTO.class));
                targetGroups.add(targetGroupDTO);
            }

            // 组装数据
            targetCategory.setPropertyRelations(targetRelations);
            targetCategory.setPropertyGroups(targetGroups);

            categoryService.save(targetCategory);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
