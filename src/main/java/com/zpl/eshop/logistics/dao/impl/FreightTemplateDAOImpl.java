package com.zpl.eshop.logistics.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.logistics.dao.FreightTemplateDAO;
import com.zpl.eshop.logistics.domain.FreightTemplateDO;
import com.zpl.eshop.logistics.domain.FreightTemplateQuery;
import com.zpl.eshop.logistics.mapper.FreightTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运费模版管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:54
 **/
@Repository
public class FreightTemplateDAOImpl implements FreightTemplateDAO {

    /**
     * 运费模版管理Mapper
     */
    @Autowired
    private FreightTemplateMapper freightTemplateMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增运费模板
     *
     * @param freightTemplate 运费模板
     */
    @Override
    public void save(FreightTemplateDO freightTemplate) throws Exception {
        freightTemplate.setGmtCreate(dateProvider.getCurrentTime());
        freightTemplate.setGmtModified(dateProvider.getCurrentTime());
        freightTemplateMapper.save(freightTemplate);
    }

    /**
     * 分页查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    @Override
    public List<FreightTemplateDO> listByPage(FreightTemplateQuery query) {
        return freightTemplateMapper.listByPage(query);
    }

    /**
     * 根据id查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    @Override
    public FreightTemplateDO getById(Long id) {
        return freightTemplateMapper.getById(id);
    }

    /**
     * 更新运费模板
     *
     * @param freightTemplate 运费模板
     */
    @Override
    public void update(FreightTemplateDO freightTemplate) throws Exception {
        freightTemplate.setGmtModified(dateProvider.getCurrentTime());
        freightTemplateMapper.update(freightTemplate);
    }
}
