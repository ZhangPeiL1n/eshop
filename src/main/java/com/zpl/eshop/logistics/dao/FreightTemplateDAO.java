package com.zpl.eshop.logistics.dao;

import com.zpl.eshop.logistics.domain.FreightTemplateDO;
import com.zpl.eshop.logistics.domain.FreightTemplateQuery;

import java.util.List;

/**
 * 运费模版管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:53
 **/
public interface FreightTemplateDAO {

    /**
     * 新增运费模板
     *
     * @param freightTemplate 运费模板
     */
    void save(FreightTemplateDO freightTemplate) throws Exception;

    /**
     * 分页查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    List<FreightTemplateDO> listByPage(FreightTemplateQuery query);

    /**
     * 根据id查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    FreightTemplateDO getById(Long id);

    /**
     * 更新运费模板
     *
     * @param freightTemplate 运费模板
     */
    void update(FreightTemplateDO freightTemplate) throws Exception;
}
