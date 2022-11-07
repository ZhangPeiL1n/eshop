package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderItemDO;

import java.util.List;

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
     * @return id
     * @throws Exception
     */
    Long save(SaleDeliveryOrderItemDO saleDeliveryOrderItem) throws Exception;

    /**
     * 根据销售出库单id查询销售出库单条目
     *
     * @param saleDeliveryOrderId 销售出库单idi
     * @return 销售出库单条目
     * @throws Exception
     */
    List<SaleDeliveryOrderItemDO> listBySaleDeliveryOrderId(Long saleDeliveryOrderId) throws Exception;
}
