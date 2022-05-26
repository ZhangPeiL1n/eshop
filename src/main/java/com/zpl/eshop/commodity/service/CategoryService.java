package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.CategoryDTO;

import java.util.List;

/**
 * 类目管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:34
 **/
public interface CategoryService {
    /**
     * 查询根类目
     *
     * @return 根类目集合
     */
    List<CategoryDTO> listRoots();

    /**
     * 查询子类目
     *
     * @param id 父类目 id
     * @return 子类目集合
     */
    List<CategoryDTO> listChildren(Long id);

    /**
     * 新增类目
     *
     * @param categoryDTO 类目
     * @return 处理结果
     */
    Boolean save(CategoryDTO categoryDTO);

    /**
     * 根据id查询类目
     *
     * @param id 类目id
     * @return 类目
     */
    CategoryDTO getById(Long id);
}
