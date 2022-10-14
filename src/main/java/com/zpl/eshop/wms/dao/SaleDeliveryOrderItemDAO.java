package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderItemDO;

/**
 * 销售出库单条目DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:15
 **/
public interface SaleDeliveryOrderItemDAO {

    /**
     * 新增销售出库单条目
     *
     * @param saleDeliveryOrderItem 销售出库单条目
     */
    Long save(SaleDeliveryOrderItemDO saleDeliveryOrderItem) throws Exception;
}
