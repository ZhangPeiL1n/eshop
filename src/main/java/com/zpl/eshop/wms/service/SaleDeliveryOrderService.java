package com.zpl.eshop.wms.service;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderQuery;

import java.util.Date;
import java.util.List;

/**
 * 销售出库单管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:27
 **/
public interface SaleDeliveryOrderService {

    /**
     * 新增销售出库单
     *
     * @param saleDeliveryOrder 销售出库单
     * @throws Exception
     */
    void save(SaleDeliveryOrderDTO saleDeliveryOrder) throws Exception;

    /**
     * 分页查询销售出库单
     *
     * @param query 查询条件
     * @return 销售出库单
     */
    List<SaleDeliveryOrderDTO> listByPage(SaleDeliveryOrderQuery query) throws Exception;

    /**
     * 根据id 查询销售出库单
     *
     * @param id 销售出库单id
     * @return 销售出库单
     * @throws Exception
     */
    SaleDeliveryOrderDTO getById(Long id) throws Exception;

    /**
     * 更新销售出库单的发货时间
     *
     * @param id           销售出库单id
     * @param deliveryTime 发货时间
     */
    void updateDeliveryTime(Long id, Date deliveryTime) throws Exception;

    /**
     * 对销售出库单提交审核
     *
     * @param id 销售出库单id
     * @throws Exception
     */
    void submitApprove(Long id) throws Exception;

    /**
     * 审核销售出库单
     *
     * @param id            销售出库单id
     * @param approveResult 审核结果
     * @throws Exception
     */
    void approve(Long id, Integer approveResult) throws Exception;
}
