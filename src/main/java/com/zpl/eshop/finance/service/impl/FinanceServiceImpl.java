package com.zpl.eshop.finance.service.impl;

import com.zpl.eshop.finance.service.FinanceService;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 财务中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 19:04
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceServiceImpl implements FinanceService {

    /**
     * 创建采购结算单
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean createPurchaseSettlementOrder(PurchaseInputOrderDTO purchaseInputOrder) {
        return true;
    }

    /**
     * 给物流公司打款
     *
     * @param saleDeliveryOrder 销售出库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean payForLogisticsCompany(SaleDeliveryOrderDTO saleDeliveryOrder) {
        return true;
    }
}
