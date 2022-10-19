package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.SaleDeliveryOrderSendOutDetailDAO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDO;
import com.zpl.eshop.wms.mapper.SaleDeliveryOrderSendOutDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增销售出库单发货明细
     *
     * @param sendOutDetail 销售出库单发货明细
     */
    @Override
    public void save(SaleDeliveryOrderSendOutDetailDO sendOutDetail) throws Exception {
        sendOutDetail.setGmtCreate(dateProvider.getCurrentTime());
        sendOutDetail.setGmtModified(dateProvider.getCurrentTime());
        sendOutDetailMapper.save(sendOutDetail);
    }

    /**
     * 根据销售出库单条目id查询发货明细
     *
     * @param saleDeliveryOrderItemId 销售出库单id
     * @return 发货明细
     */
    @Override
    public List<SaleDeliveryOrderSendOutDetailDO> listBySaleDeliveryOrderItemId(Long saleDeliveryOrderItemId) {
        return sendOutDetailMapper.listBySaleDeliveryOrderItemId(saleDeliveryOrderItemId);
    }
}
