package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.PropertyGroupDO;

import java.util.List;

/**
 * 属性分组DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/5/18 0:25
 **/

public interface PropertyGroupDAO {
    /**
     * 新增属性分组
     *
     * @param propertyGroupDO 属性分组
     * @return 新增成功
     * @throws Exception
     */
    Long save(PropertyGroupDO propertyGroupDO) throws Exception;

    /**
     * 根据类目id查找属性分组
     *
     * @param categoryId 类目id
     * @return 属性分组
     * @throws Exception
     */
    List<PropertyGroupDO> listByCategoryId(Long categoryId) throws Exception;

    /**
     * 根据类目id删除属性分组
     *
     * @param categoryId 类目id
     * @throws Exception
     */
    void removeByCategoryId(Long categoryId) throws Exception;
}
