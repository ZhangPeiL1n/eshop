package com.zpl.eshop.customer.service;

import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDTO;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetQuery;

import java.util.List;

/**
 * 客服中心退货工单管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 21:59
 **/
public interface ReturnGoodsWorksheetService {

    /**
     * 分页查询退货工单
     *
     * @param query 查询条件
     * @return 退货工单
     */
    List<ReturnGoodsWorksheetDTO> listByPage(ReturnGoodsWorksheetQuery query) throws Exception;

    /**
     * 根据id查询退货工单
     *
     * @param id 退货工单id
     * @return 退货工单
     */
    ReturnGoodsWorksheetDTO getById(Long id) throws Exception;
}
