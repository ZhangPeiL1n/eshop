package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.SaleDeliveryOrderItemDAO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderItemDO;
import com.zpl.eshop.wms.mapper.SaleDeliveryOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增销售出库单条目
     *
     * @param saleDeliveryOrderItem 销售出库单条目
     */
    @Override
    public Long save(SaleDeliveryOrderItemDO saleDeliveryOrderItem) throws Exception {
        saleDeliveryOrderItem.setGmtCreate(dateProvider.getCurrentTime());
        saleDeliveryOrderItem.setGmtModified(dateProvider.getCurrentTime());
        saleDeliveryOrderItemMapper.save(saleDeliveryOrderItem);
        return saleDeliveryOrderItem.getId();
    }

    /**
     * 根据销售出库单id查询销售出库单条目
     *
     * @param saleDeliveryOrderId 销售出库单idi
     * @return 销售出库单条目
     */
    @Override
    public List<SaleDeliveryOrderItemDO> listBySaleDeliveryOrderId(Long saleDeliveryOrderId) {
        return saleDeliveryOrderItemMapper.listBySaleDeliveryOrderId(saleDeliveryOrderId);
    }
}
