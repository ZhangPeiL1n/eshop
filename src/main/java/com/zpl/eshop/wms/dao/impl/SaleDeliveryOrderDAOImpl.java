package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.SaleDeliveryOrderDAO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderQuery;
import com.zpl.eshop.wms.mapper.SaleDeliveryOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 销售出库单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:13
 **/
@Repository
public class SaleDeliveryOrderDAOImpl implements SaleDeliveryOrderDAO {

    /**
     * 销售出库单管理mapper组件
     */
    @Autowired
    private SaleDeliveryOrderMapper saleDeliveryOrderMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增销售出库单
     *
     * @param saleDeliveryOrder 销售出库单
     */
    @Override
    public Long save(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception {
        saleDeliveryOrder.setGmtCreate(dateProvider.getCurrentTime());
        saleDeliveryOrder.setGmtModified(dateProvider.getCurrentTime());
        saleDeliveryOrderMapper.save(saleDeliveryOrder);
        return saleDeliveryOrder.getId();
    }

    /**
     * 分页查询销售出库单
     *
     * @param query 查询条件
     * @return 销售出库单
     */
    @Override
    public List<SaleDeliveryOrderDO> listByPage(SaleDeliveryOrderQuery query) throws Exception {
        return saleDeliveryOrderMapper.listByPage(query);
    }

    /**
     * 根据id查询销售出库单
     *
     * @param id 销售出库单id
     * @return 销售出库单
     */
    @Override
    public SaleDeliveryOrderDO getById(Long id) {
        return saleDeliveryOrderMapper.getById(id);
    }

    /**
     * 根据订单id查询销售出库单
     *
     * @param orderId 销售出库单id
     * @return 销售出库单
     */
    @Override
    public SaleDeliveryOrderDO getByOrderId(Long orderId) {
        return saleDeliveryOrderMapper.getByOrderId(orderId);
    }

    /**
     * 更新销售出库单
     *
     * @param saleDeliveryOrder 销售出库单
     */
    @Override
    public void update(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception {
        saleDeliveryOrder.setGmtModified(dateProvider.getCurrentTime());
        saleDeliveryOrderMapper.update(saleDeliveryOrder);
    }
}
