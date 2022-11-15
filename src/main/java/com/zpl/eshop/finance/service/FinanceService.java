package com.zpl.eshop.finance.service;

import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;

/**
 * 财务中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:41
 **/
public interface FinanceService {

    /**
     * 创建采购结算单
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean createPurchaseSettlementOrder(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

    /**
     * 给物流公司打款
     *
     * @param saleDeliveryOrder 销售出库单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean payForLogisticsCompany(SaleDeliveryOrderDTO saleDeliveryOrder) throws Exception;
}
