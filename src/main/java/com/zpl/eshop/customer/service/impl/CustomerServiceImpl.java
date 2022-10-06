package com.zpl.eshop.customer.service.impl;

import com.zpl.eshop.customer.constant.ReturnGoodsWorksheetStatus;
import com.zpl.eshop.customer.dao.ReturnGoodsWorksheetDAO;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDO;
import com.zpl.eshop.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客服中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 21:43
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    /**
     * 客服中心退货工单管理DAO组件
     */
    @Autowired
    private ReturnGoodsWorksheetDAO returnGoodsWorksheetDAO;

    /**
     * 创建退货工单
     *
     * @param orderId           订单 id
     * @param orderNo           订单编号
     * @param returnGoodsReason 退货原因
     * @param returnGoodsRemark 退货备注
     * @return 处理结果
     */
    @Override
    public Boolean createReturnGoodsWorkSheet(
            Long orderId, String orderNo,
            Integer returnGoodsReason, String returnGoodsRemark) {
        try {
            ReturnGoodsWorksheetDO worksheet = new ReturnGoodsWorksheetDO();
            worksheet.setOrderId(orderId);
            worksheet.setOrderNo(orderNo);
            worksheet.setReturnGoodsReason(returnGoodsReason);
            worksheet.setReturnGoodsRemark(returnGoodsRemark);
            worksheet.setStatus(ReturnGoodsWorksheetStatus.WAIT_FOR_APPROVE);

            returnGoodsWorksheetDAO.save(worksheet);

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 同步物流单号
     *
     * @param orderInfoId              订单id
     * @param returnGoodsLogisticsCode 退货物流单号
     * @return 处理结果
     */
    @Override
    public Boolean syncReturnGoodsLogisticsCode(Long orderInfoId, String returnGoodsLogisticsCode) {
        return true;
    }

    /**
     * 通知客服中心，“完成退货入库”事件发生了
     *
     * @param returnGoodsWorksheetId 退货工单id
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputFinishEvent(Long returnGoodsWorksheetId) {
        return true;
    }

    /**
     * 通知客服中心，“完成退款”事件发生了
     *
     * @param returnGoodsWorksheetId 退货工单id
     * @return 处理结果
     */
    @Override
    public Boolean informRefundFinishEvent(Long returnGoodsWorksheetId) {
        return true;
    }
}
