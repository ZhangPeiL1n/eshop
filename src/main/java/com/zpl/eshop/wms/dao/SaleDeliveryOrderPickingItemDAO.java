package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderPickingItemDO;

import java.util.List;

/**
 * 销售出库单拣货条目DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:17
 **/
public interface SaleDeliveryOrderPickingItemDAO {

    /**
     * 新增销售出库单拣货条目
     *
     * @param pickingItem 拣货条目
     */
    void save(SaleDeliveryOrderPickingItemDO pickingItem) throws Exception;

    /**
     * 根据销售出库单条目id查询拣货条目
     *
     * @param saleDeliveryOrderItemId 销售出库单条目id
     * @return 拣货条目
     */
    List<SaleDeliveryOrderPickingItemDO> listBySaleDeliveryOrderItemId(Long saleDeliveryOrderItemId);
}
