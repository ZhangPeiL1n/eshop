package com.zpl.eshop.logistics.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.logistics.dao.FreightTemplateDAO;
import com.zpl.eshop.logistics.domain.FreightTemplateDO;
import com.zpl.eshop.logistics.domain.FreightTemplateDTO;
import com.zpl.eshop.logistics.domain.FreightTemplateQuery;
import com.zpl.eshop.logistics.service.FreightTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 运费模版管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:58
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class FreightTemplateServiceImpl implements FreightTemplateService {

    /**
     * 运费模板管理DAO组件
     */
    @Autowired
    private FreightTemplateDAO freightTemplateDAO;

    /**
     * 新增运费模板
     *
     * @param freightTemplate 运费模板
     */
    @Override
    public void save(FreightTemplateDTO freightTemplate) throws Exception {
        freightTemplateDAO.save(freightTemplate.clone(FreightTemplateDO.class));
    }

    /**
     * 分页查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    @Override
    public List<FreightTemplateDTO> listByPage(FreightTemplateQuery query) throws Exception {
        return ObjectUtils.convertList(
                freightTemplateDAO.listByPage(query),
                FreightTemplateDTO.class);
    }

    /**
     * 根据id查询运费模板
     *
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    @Override
    public FreightTemplateDTO getById(Long id) throws Exception {
        return freightTemplateDAO.getById(id).clone(FreightTemplateDTO.class);
    }

    /**
     * 更新运费模板
     *
     * @param freightTemplate 运费模板
     */
    @Override
    public void update(FreightTemplateDTO freightTemplate) throws Exception {
        freightTemplateDAO.update(freightTemplate.clone(FreightTemplateDO.class));
    }
}
