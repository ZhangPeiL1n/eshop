package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDO;

/**
 * 销售出库单发货明细DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:19
 **/
public interface SaleDeliveryOrderSendOutDetailDAO {

    /**
     * 新增销售出库单发货明细
     *
     * @param sendOutDetail 销售出库单发货明细
     */
    void save(SaleDeliveryOrderSendOutDetailDO sendOutDetail) throws Exception;
}
