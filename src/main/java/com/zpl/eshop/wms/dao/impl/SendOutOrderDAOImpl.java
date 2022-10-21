package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.SendOutOrderDAO;
import com.zpl.eshop.wms.domain.SendOutOrderDO;
import com.zpl.eshop.wms.mapper.SendOutOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 发货单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:22
 **/
@Repository
public class SendOutOrderDAOImpl implements SendOutOrderDAO {

    /**
     * 发货单管理mapper组件
     */
    @Autowired
    private SendOutOrderMapper sendOutOrderMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增发货单
     *
     * @param sendOutOrder 发货单
     */
    @Override
    public Long save(SendOutOrderDO sendOutOrder) throws Exception {
        sendOutOrder.setGmtCreate(dateProvider.getCurrentTime());
        sendOutOrder.setGmtModified(dateProvider.getCurrentTime());
        sendOutOrderMapper.save(sendOutOrder);
        return sendOutOrder.getId();
    }

    /**
     * 根据销售出库单id查询发货单
     *
     * @param saleDeliveryOrderId 销售出库单id
     * @return 发货单
     */
    @Override
    public SendOutOrderDO getBySaleDeliveryOrderId(Long saleDeliveryOrderId) {
        return sendOutOrderMapper.getBySaleDeliveryOrderId(saleDeliveryOrderId);
    }
}
