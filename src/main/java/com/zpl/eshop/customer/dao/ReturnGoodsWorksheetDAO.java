package com.zpl.eshop.customer.dao;

import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDO;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetQuery;

import java.util.List;

/**
 * 客服中心退货工单管理DAO组件组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 21:53
 **/
public interface ReturnGoodsWorksheetDAO {

    /**
     * 新增退货工单
     *
     * @param worksheet 退货工单
     * @throws Exception
     */
    void save(ReturnGoodsWorksheetDO worksheet) throws Exception;

    /**
     * 分页查询退货工单
     *
     * @param query 查询条件
     * @return 退货工单
     */
    List<ReturnGoodsWorksheetDO> listByPage(ReturnGoodsWorksheetQuery query);

    /**
     * 根据id查询退货工单
     *
     * @param id 退货工单id
     * @return 退货工单
     */
    ReturnGoodsWorksheetDO getById(Long id);

    /**
     * 根据订单id查询退货工单
     *
     * @param orderInfoId 订单id
     * @return 退货工单
     */
    ReturnGoodsWorksheetDO getByOrderInfoId(Long orderInfoId);

    /**
     * 更新退货工单的状态
     *
     * @param worksheet 退货工单
     */
    void updateStatus(ReturnGoodsWorksheetDO worksheet);

    /**
     * 更新退货工单的退货物流单号
     *
     * @param worksheet 退货工单
     */
    void updateReturnGoodsLogisticsCode(ReturnGoodsWorksheetDO worksheet);
}
