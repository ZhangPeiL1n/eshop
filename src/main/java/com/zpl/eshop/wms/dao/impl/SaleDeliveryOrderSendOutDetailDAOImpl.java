package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.SaleDeliveryOrderSendOutDetailDAO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDO;
import com.zpl.eshop.wms.mapper.SaleDeliveryOrderSendOutDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 销售出库单发货明细DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:20
 **/
@Repository
public class SaleDeliveryOrderSendOutDetailDAOImpl implements SaleDeliveryOrderSendOutDetailDAO {

    /**
     * 销售出库单发货明细管理Mapper组件
     */
    @Autowired
    private SaleDeliveryOrderSendOutDetailMapper sendOutDetailMapper;

    /**
     * 新增销售出库单发货明细
     *
     * @param sendOutDetail 销售出库单发货明细
     */
    @Override
    public void save(SaleDeliveryOrderSendOutDetailDO sendOutDetail) throws Exception {
        sendOutDetailMapper.save(sendOutDetail);
    }
}
