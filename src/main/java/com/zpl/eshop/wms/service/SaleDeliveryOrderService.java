package com.zpl.eshop.wms.service;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderQuery;

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
}
