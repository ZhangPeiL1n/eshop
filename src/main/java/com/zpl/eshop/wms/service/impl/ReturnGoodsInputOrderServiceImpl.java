package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.customer.service.CustomerService;
import com.zpl.eshop.membership.service.MembershipService;
import com.zpl.eshop.pay.service.PayService;
import com.zpl.eshop.schedule.service.ScheduleService;
import com.zpl.eshop.wms.constant.ReturnGoodsInputOrderApproveResult;
import com.zpl.eshop.wms.constant.ReturnGoodsInputOrderStatus;
import com.zpl.eshop.wms.constant.WmsStockUpdateEvent;
import com.zpl.eshop.wms.dao.ReturnGoodsInputOrderDAO;
import com.zpl.eshop.wms.dao.ReturnGoodsInputOrderItemDAO;
import com.zpl.eshop.wms.dao.ReturnGoodsInputOrderPutOnItemDAO;
import com.zpl.eshop.wms.domain.*;
import com.zpl.eshop.wms.service.ReturnGoodsInputOrderService;
import com.zpl.eshop.wms.stock.WmsStockUpdater;
import com.zpl.eshop.wms.stock.WmsStockUpdaterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 退货入库管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/24 22:36
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsInputOrderServiceImpl implements ReturnGoodsInputOrderService {

    /**
     * 退货入库单管理DAO组件
     */
    @Autowired
    private ReturnGoodsInputOrderDAO returnGoodsInputOrderDAO;

    /**
     * 退货入库单条目管理DAO组件
     */
    @Autowired
    private ReturnGoodsInputOrderItemDAO returnGoodsInputOrderItemDAO;

    /**
     * 退货入库单上架条目管理DAO组件
     */
    @Autowired
    private ReturnGoodsInputOrderPutOnItemDAO putOnItemDAO;

    /**
     * 客服中心接口
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 支付中心接口
     */
    @Autowired
    private PayService payService;

    /**
     * 库存更新组件工厂
     */
    @Autowired
    private WmsStockUpdaterFactory stockUpdaterFactory;

    /**
     * 调度中心接口
     */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 会员中心接口
     */
    @Autowired
    private MembershipService membershipService;

    /**
     * 新增退货入库单
     *
     * @param returnGoodsInputOrder 退货入库单
     * @throws Exception
     */
    @Override
    public void save(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
        Long returnGoodsInputOrderId = returnGoodsInputOrderDAO.save(
                returnGoodsInputOrder.clone(ReturnGoodsInputOrderDO.class));

        for (ReturnGoodsInputOrderItemDTO item : returnGoodsInputOrder.getItems()) {
            item.setReturnGoodsInputOrderId(returnGoodsInputOrderId);
            returnGoodsInputOrderItemDAO.save(item.clone(ReturnGoodsInputOrderItemDO.class));
        }
    }

    /**
     * 分页查询退货入库单
     *
     * @param query 查询条件
     * @return 退货入库单
     * @throws Exception
     */
    @Override
    public List<ReturnGoodsInputOrderDTO> listByPage(ReturnGoodsInputOrderQuery query) throws Exception {
        return ObjectUtils.convertList(
                returnGoodsInputOrderDAO.listByPage(query),
                ReturnGoodsInputOrderDTO.class);
    }

    /**
     * 根据id查询退货入库单
     *
     * @param id 退货入库单id
     * @return 退货入库单
     * @throws Exception
     */
    @Override
    public ReturnGoodsInputOrderDTO getById(Long id) throws Exception {
        ReturnGoodsInputOrderDTO returnGoodsInputOrder = returnGoodsInputOrderDAO.getById(id)
                .clone(ReturnGoodsInputOrderDTO.class);

        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems = ObjectUtils.convertList(
                returnGoodsInputOrderItemDAO.listByReturnGoodsInputOrderId(id),
                ReturnGoodsInputOrderItemDTO.class);
        returnGoodsInputOrder.setItems(returnGoodsInputOrderItems);

        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrderItems) {
            List<ReturnGoodsInputOrderPutOnItemDTO> putOnItems = ObjectUtils.convertList(
                    putOnItemDAO.listByReturnGoodsInputOrderItemId(returnGoodsInputOrderItem.getId()),
                    ReturnGoodsInputOrderPutOnItemDTO.class);
            returnGoodsInputOrderItem.setPutOnItems(putOnItems);
        }

        return returnGoodsInputOrder;
    }

    /**
     * 更新退货入库单
     *
     * @param returnGoodsInputOrder 退货入库单
     * @throws Exception
     */
    @Override
    public void update(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
        returnGoodsInputOrder.setStatus(ReturnGoodsInputOrderStatus.EDITING);
        returnGoodsInputOrderDAO.update(returnGoodsInputOrder.clone(ReturnGoodsInputOrderDO.class));

        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrder.getItems()) {
            returnGoodsInputOrderItemDAO.update(returnGoodsInputOrderItem.clone(ReturnGoodsInputOrderItemDO.class));
        }
    }

    /**
     * 批量新增退货入库单上架条目
     *
     * @param returnGoodsInputOrder 退货入库单
     * @throws Exception
     */
    @Override
    public void batchSavePutOnItems(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrder.getItems()) {
            for (ReturnGoodsInputOrderPutOnItemDTO putOnItem : returnGoodsInputOrderItem.getPutOnItems()) {
                putOnItemDAO.save(putOnItem.clone(ReturnGoodsInputOrderPutOnItemDO.class));
            }
        }
    }

    /**
     * 退货入库单提交审核
     *
     * @param id 退货入库单id
     * @throws Exception
     */
    @Override
    public void submitApprove(Long id) throws Exception {
        returnGoodsInputOrderDAO.updateStatus(id, ReturnGoodsInputOrderStatus.WAIT_FOR_APPROVE);
    }

    /**
     * 审核退货入库单
     *
     * @param id            退货入库单id
     * @param approveResult 审核结果
     * @throws Exception
     */
    @Override
    public void approve(Long id, Integer approveResult) throws Exception {
        if (ReturnGoodsInputOrderApproveResult.REJECTED.equals(approveResult)) {
            returnGoodsInputOrderDAO.updateStatus(id, ReturnGoodsInputOrderStatus.EDITING);
            return;
        }

        ReturnGoodsInputOrderDTO returnGoodsInputOrder = getById(id);
        returnGoodsInputOrderDAO.updateStatus(id, ReturnGoodsInputOrderStatus.FINISHED);
        customerService.informReturnGoodsInputFinishedEvent(returnGoodsInputOrder.getReturnGoodsWorksheetId());

        Boolean refundResult = payService.refund(returnGoodsInputOrder);
        if (refundResult) {
            customerService.informRefundFinishedEvent(returnGoodsInputOrder.getReturnGoodsWorksheetId());
        }

        WmsStockUpdater stockUpdater = stockUpdaterFactory.create(
                WmsStockUpdateEvent.RETURN_GOODS_INPUT, returnGoodsInputOrder);
        stockUpdater.update();

        scheduleService.informReturnGoodsInputFinished(returnGoodsInputOrder);

        membershipService.informFinishReturnGoodsEvent(returnGoodsInputOrder.getUserAccountId(),
                returnGoodsInputOrder.getPayableAmount());
    }
}
