package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.LogisticOrderDAO;
import com.zpl.eshop.wms.domain.LogisticOrderDO;
import com.zpl.eshop.wms.mapper.LogisticOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 物流单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:25
 **/
@Repository
public class LogisticOrderDAOImpl implements LogisticOrderDAO {

    /**
     * 物流单管理mapper组件
     */
    @Autowired
    private LogisticOrderMapper logisticOrderMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增物流单
     *
     * @param logisticOrder 物流单
     */
    @Override
    public void save(LogisticOrderDO logisticOrder) throws Exception {
        logisticOrder.setGmtCreate(dateProvider.getCurrentTime());
        logisticOrder.setGmtModified(dateProvider.getCurrentTime());
        logisticOrderMapper.save(logisticOrder);
    }

    /**
     * 根据销售出库单id查询物流单
     *
     * @param saleDeliveryOrderId 销售出库单id
     * @return 物流单
     */
    @Override
    public LogisticOrderDO getBySaleDeliveryOrderId(Long saleDeliveryOrderId) {
        return logisticOrderMapper.getBySaleDeliveryOrderId(saleDeliveryOrderId);
    }

}
