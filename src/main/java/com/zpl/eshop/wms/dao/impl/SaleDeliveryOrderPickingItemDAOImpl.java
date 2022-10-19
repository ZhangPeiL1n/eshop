package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.SaleDeliveryOrderPickingItemDAO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderPickingItemDO;
import com.zpl.eshop.wms.mapper.SaleDeliveryOrderPickingItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 销售出库单拣货条目管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:18
 **/
@Repository
public class SaleDeliveryOrderPickingItemDAOImpl implements SaleDeliveryOrderPickingItemDAO {

    /**
     * 销售出库单拣货条目管理mapper组件
     */
    @Autowired
    private SaleDeliveryOrderPickingItemMapper pickingItemMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增销售出库单拣货条目
     *
     * @param pickingItem
     */
    @Override
    public void save(SaleDeliveryOrderPickingItemDO pickingItem) throws Exception {
        pickingItem.setGmtCreate(dateProvider.getCurrentTime());
        pickingItem.setGmtModified(dateProvider.getCurrentTime());
        pickingItemMapper.save(pickingItem);
    }

    /**
     * 根据销售出库单条目id查询拣货条目
     *
     * @param saleDeliveryOrderItemId 销售出库单条目id
     * @return 拣货条目
     */
    @Override
    public List<SaleDeliveryOrderPickingItemDO> listBySaleDeliveryOrderItemId(Long saleDeliveryOrderItemId) {
        return pickingItemMapper.listBySaleDeliveryOrderItemId(saleDeliveryOrderItemId);
    }
}
