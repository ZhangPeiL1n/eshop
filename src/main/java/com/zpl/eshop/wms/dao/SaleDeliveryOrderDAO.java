package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderDO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderQuery;

import java.util.List;

/**
 * 销售出库单管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:12
 **/
public interface SaleDeliveryOrderDAO {

    /**
     * 新增销售出库单
     *
     * @param saleDeliveryOrder 销售出库单
     */
    Long save(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception;

    /**
     * 分页查询销售出库单
     *
     * @param query 查询条件
     * @return 销售出库单
     */
    List<SaleDeliveryOrderDO> listByPage(SaleDeliveryOrderQuery query) throws Exception;
}
