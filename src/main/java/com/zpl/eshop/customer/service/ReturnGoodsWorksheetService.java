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
     * @throws Exception
     */
    List<ReturnGoodsWorksheetDTO> listByPage(ReturnGoodsWorksheetQuery query) throws Exception;

    /**
     * 根据id查询退货工单
     *
     * @param id 退货工单id
     * @return 退货工单
     * @throws Exception
     */
    ReturnGoodsWorksheetDTO getById(Long id) throws Exception;


    /**
     * 审核退货工单
     *
     * @param id            退货工单id
     * @param approveResult 审核结果
     * @throws Exception
     */
    void approve(Long id, Integer approveResult) throws Exception;

    /**
     * 确认退货工单已经收到了退货商品
     *
     * @param id 退货工单id
     * @throws Exception
     */
    void confirmReceivedReturnGoods(Long id) throws Exception;

}
