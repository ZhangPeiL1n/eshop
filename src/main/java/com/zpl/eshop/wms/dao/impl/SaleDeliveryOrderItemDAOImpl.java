package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.SaleDeliveryOrderItemDAO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderItemDO;
import com.zpl.eshop.wms.mapper.SaleDeliveryOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 销售出库单条目DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:15
 **/
@Repository
public class SaleDeliveryOrderItemDAOImpl implements SaleDeliveryOrderItemDAO {

    /**
     * 销售出库单条目管理Mapper
     */
    @Autowired
    private SaleDeliveryOrderItemMapper saleDeliveryOrderItemMapper;

    /**
     * 新增销售出库单条目
     *
     * @param saleDeliveryOrderItem 销售出库单条目
     */
    @Override
    public Long save(SaleDeliveryOrderItemDO saleDeliveryOrderItem) throws Exception {
        saleDeliveryOrderItemMapper.save(saleDeliveryOrderItem);
        return saleDeliveryOrderItem.getId();
    }
}
