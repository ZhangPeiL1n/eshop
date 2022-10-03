package com.zpl.eshop.customer.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.customer.dao.ReturnGoodsWorksheetDAO;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDTO;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetQuery;
import com.zpl.eshop.customer.service.ReturnGoodsWorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/3 22:00
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsWorksheetServiceImpl implements ReturnGoodsWorksheetService {

    /**
     * 客服中心退货工单管理DAO组件
     */
    @Autowired
    private ReturnGoodsWorksheetDAO returnGoodsWorksheetDAO;

    /**
     * 分页查询退货工单
     *
     * @param query 查询条件
     * @return 退货工单
     */
    @Override
    public List<ReturnGoodsWorksheetDTO> listByPage(ReturnGoodsWorksheetQuery query) throws Exception {
        return ObjectUtils.convertList(returnGoodsWorksheetDAO.listByPage(query), ReturnGoodsWorksheetDTO.class);
    }

    /**
     * 根据id查询退货工单
     *
     * @param id 退货工单id
     * @return 退货工单
     */
    @Override
    public ReturnGoodsWorksheetDTO getById(Long id) throws Exception {
        return returnGoodsWorksheetDAO.getById(id).clone(ReturnGoodsWorksheetDTO.class);
    }
}
