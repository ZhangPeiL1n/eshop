package com.zpl.eshop.finance.service.impl;

import com.zpl.eshop.finance.domain.PurchaseSettlementOrderDTO;
import com.zpl.eshop.finance.domain.PurchaseSettlementOrderItemDTO;
import com.zpl.eshop.finance.service.FinanceService;
import com.zpl.eshop.finance.service.PurchaseSettlementOrderService;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 财务中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 19:04
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceServiceImpl implements FinanceService {

    private static final Logger logger = LoggerFactory.getLogger(FinanceServiceImpl.class);

    /**
     * 采购结算单service组件
     */
    @Autowired
    private PurchaseSettlementOrderService purchaseSettlementOrderService;

    /**
     * 创建采购结算单
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean createPurchaseSettlementOrder(PurchaseInputOrderDTO purchaseInputOrder) {
        try {
            // 将采购入库单的数据拷贝到采购结算中去
            PurchaseSettlementOrderDTO purchaseSettlementOrder =
                    purchaseInputOrder.clone(PurchaseSettlementOrderDTO.class);
            purchaseSettlementOrder.setId(null);
            purchaseSettlementOrder.setStatus(null);
            purchaseSettlementOrder.setGmtCreate(null);
            purchaseSettlementOrder.setGmtModified(null);
            purchaseSettlementOrder.setPurchaseInputOrderId(purchaseInputOrder.getId());

            // 将采购入库单条目的数据拷贝到采购结算单条目中去
            List<PurchaseSettlementOrderItemDTO> items = new ArrayList<>();

            for (PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrder.getItems()) {
                PurchaseSettlementOrderItemDTO item = purchaseInputOrderItem.clone(PurchaseSettlementOrderItemDTO.class);
                item.setId(null);
                item.setGmtCreate(null);
                item.setGmtModified(null);

                items.add(item);
            }

            purchaseSettlementOrder.setItems(items);

            // 完成采购结算单的新增
            purchaseSettlementOrderService.save(purchaseSettlementOrder);

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 给物流公司打款
     *
     * @param saleDeliveryOrder 销售出库单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean payForLogisticsCompany(SaleDeliveryOrderDTO saleDeliveryOrder) {
        return true;
    }
}
