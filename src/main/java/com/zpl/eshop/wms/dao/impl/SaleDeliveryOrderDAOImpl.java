package com.zpl.eshop.wms.dao.impl;

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
     * 新增销售出库单
     *
     * @param saleDeliveryOrder 销售出库单
     */
    @Override
    public Long save(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception {
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
}
