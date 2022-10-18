package com.zpl.eshop.purchase.service.impl;

import com.zpl.eshop.purchase.domain.SupplierDTO;
import com.zpl.eshop.purchase.service.PurchaseService;
import com.zpl.eshop.purchase.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 15:51
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    /**
     * 供应商管理Service组件
     */
    @Autowired
    private SupplierService supplierService;

    /**
     * 判断是否有关联商品 sku 的采购单
     *
     * @param goodsSkuId 商品 sku id
     * @return 是否有采购单关联了商品 sku
     */
    @Override
    public Boolean existRelatedPurchaseOrder(Long goodsSkuId) {
        return true;
    }

    /**
     * 通知采购中心，“创建采购入库单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    @Override
    public Boolean informCreatePurchaseInputOrderEvent(Long purchaseOrderId) {
        return true;
    }

    /**
     * 通知采购中心，“完成采购入库”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    @Override
    public Boolean informFinishedPurchaseInputOrderEvent(Long purchaseOrderId) {
        return true;
    }

    /**
     * 通知采购中心，“创建采购结算单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    @Override
    public Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseOrderId) {
        return true;
    }

    /**
     * 通知采购中心，“创建采购结算单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    @Override
    public Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseOrderId) {
        return true;
    }

    /**
     * 根据结算周期查询供应商
     *
     * @param settlementPeriod 结算周期
     * @return 供应商
     */
    @Override
    public List<SupplierDTO> listSupplierBySettlementPeriod(Integer settlementPeriod) {
        try {
            return supplierService.listBySettlementPeriod(settlementPeriod);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
