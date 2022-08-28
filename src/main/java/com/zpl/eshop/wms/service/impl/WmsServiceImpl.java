package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;
import com.zpl.eshop.wms.service.WmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/28 12:39
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class WmsServiceImpl implements WmsService {

    private static final Logger logger = LoggerFactory.getLogger(WmsServiceImpl.class);

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 创建采购入库单
     *
     * @param purchaseInputOrderDTO 采购入库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        return true;
    }

    /**
     * 创建销售出库单
     *
     * @param saleDeliveryOrderDTO 销售出库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrderDTO) {
        return true;
    }

    /**
     * 创建退货入库单
     *
     * @param returnGoodsInputOrderDTO 退货入库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        return true;
    }

    /**
     * 通知 wms中心，“提交订单“事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知 wms中心，“支付订单”事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知 wms中心，“取消订单”事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean informCancelOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 根据商品 skuId 查询货位库存明细
     *
     * @param goodsSkuId 商品sku
     * @return 货位库存明细
     */
    @Override
    public List<GoodsAllocationStockDetailDTO> listStockDetailsByGoodsSkuId(Long goodsSkuId) {
        List<GoodsAllocationStockDetailDTO> stockDetails = new ArrayList<>();
        try {
            GoodsAllocationStockDetailDTO stockDetail1 = new GoodsAllocationStockDetailDTO();
            stockDetail1.setId(1L);
            stockDetail1.setGoodsSkuId(goodsSkuId);
            stockDetail1.setGoodsAllocationId(1L);
            stockDetail1.setPutOnTime(dateProvider.parse2Datetime("2022-08-28 20:33:00"));
            stockDetail1.setPutOnQuantity(40L);
            stockDetail1.setCurrentStockQuantity(40L);
            stockDetail1.setGmtCreate(dateProvider.getCurrentTime());
            stockDetail1.setGmtModified(dateProvider.getCurrentTime());
            stockDetails.add(stockDetail1);

            GoodsAllocationStockDetailDTO stockDetail2 = new GoodsAllocationStockDetailDTO();
            stockDetail2.setId(2L);
            stockDetail2.setGoodsSkuId(goodsSkuId);
            stockDetail2.setGoodsAllocationId(1L);
            stockDetail2.setPutOnTime(dateProvider.parse2Datetime("2022-08-28 21:33:00"));
            stockDetail2.setPutOnQuantity(60L);
            stockDetail2.setCurrentStockQuantity(60L);
            stockDetail2.setGmtCreate(dateProvider.getCurrentTime());
            stockDetail2.setGmtModified(dateProvider.getCurrentTime());
            stockDetails.add(stockDetail2);

            GoodsAllocationStockDetailDTO stockDetail3 = new GoodsAllocationStockDetailDTO();
            stockDetail3.setId(3L);
            stockDetail3.setGoodsSkuId(goodsSkuId);
            stockDetail3.setGoodsAllocationId(2L);
            stockDetail3.setPutOnTime(dateProvider.parse2Datetime("2022-08-28 22:33:00"));
            stockDetail3.setPutOnQuantity(150L);
            stockDetail3.setCurrentStockQuantity(150L);
            stockDetail3.setGmtCreate(dateProvider.getCurrentTime());
            stockDetail3.setGmtModified(dateProvider.getCurrentTime());
            stockDetails.add(stockDetail3);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return stockDetails;
    }


}
