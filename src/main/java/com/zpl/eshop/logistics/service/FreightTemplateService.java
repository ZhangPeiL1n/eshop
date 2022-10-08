package com.zpl.eshop.logistics.service;

import com.zpl.eshop.logistics.domain.FreightTemplateDTO;
import com.zpl.eshop.logistics.domain.FreightTemplateQuery;

import java.util.List;

/**
 * 运费管理模版Service接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:57
 **/
public interface FreightTemplateService {

    /**
     * 新增运费模板
     *
     * @param freightTemplate 运费模板
     */
    void save(FreightTemplateDTO freightTemplate) throws Exception;

    /**
     * 分页查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    List<FreightTemplateDTO> listByPage(FreightTemplateQuery query) throws Exception;

    /**
     * 根据id查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    FreightTemplateDTO getById(Long id) throws Exception;

    /**
     * 更新运费模板
     *
     * @param freightTemplate 运费模板
     */
    void update(FreightTemplateDTO freightTemplate) throws Exception;
}
