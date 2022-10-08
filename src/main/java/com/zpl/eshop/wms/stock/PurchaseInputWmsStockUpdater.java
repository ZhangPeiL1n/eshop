package com.zpl.eshop.wms.stock;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.wms.dao.GoodsAllocationStockDetailDAO;
import com.zpl.eshop.wms.dao.WmsGoodsAllocationStockDAO;
import com.zpl.eshop.wms.dao.WmsGoodsStockDAO;
import com.zpl.eshop.wms.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 采购入库更新组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:41
 **/
@Component
@Scope("prototype")
public class PurchaseInputWmsStockUpdater extends AbstractWmsStockUpdater {

    /**
     * 采购入库单
     */
    private PurchaseInputOrderDTO purchaseInputOrder;

    /**
     * 商品库存管理的DAO组件
     */
    @Autowired
    private WmsGoodsStockDAO goodsStockDAO;

    /**
     * 货位库存管理的DAO组件
     */
    @Autowired
    private WmsGoodsAllocationStockDAO goodsAllocationStockDAO;

    /**
     * 货位库存明细管理的DAO组件
     */
    @Autowired
    private GoodsAllocationStockDetailDAO stockDetailDAO;

    /**
     * 更新商品库存
     */
    @Override
    protected void updateGoodsStock() throws Exception {
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItems =
                purchaseInputOrder.getItems();
        for (PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrderItems) {
            WmsGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(
                    purchaseInputOrderItem.getGoodsSkuId());
            goodsStock.setAvailableStockQuantity(goodsStock.getAvailableStockQuantity()
                    + purchaseInputOrderItem.getArrivalCount());
            goodsStockDAO.update(goodsStock);
        }
    }

    /**
     * 更新货位库存
     */
    @Override
    protected void updateGoodsAllocationStock() throws Exception {
        List<PurchaseInputOrderItemDTO> items = purchaseInputOrder.getItems();

        for (PurchaseInputOrderItemDTO item : items) {
            List<PurchaseInputOrderPutOnItemDTO> putOnItems = item.getPutOnItems();

            for (PurchaseInputOrderPutOnItemDTO putOnItem : putOnItems) {
                WmsGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO
                        .getBySkuId(putOnItem.getGoodsAllocationId(), putOnItem.getGoodsSkuId());
                goodsAllocationStock.setAvailableStockQuantity(goodsAllocationStock.getAvailableStockQuantity()
                        + putOnItem.getPutOnShelvesCount());
                goodsAllocationStockDAO.update(goodsAllocationStock);
            }
        }
    }

    /**
     * 更新货位库存明细
     */
    @Override
    protected void updateGoodsAllocationStockDetail() throws Exception {
        List<PurchaseInputOrderItemDTO> items = purchaseInputOrder.getItems();

        for (PurchaseInputOrderItemDTO item : items) {
            List<PurchaseInputOrderPutOnItemDTO> putOnItems = item.getPutOnItems();
            List<GoodsAllocationStockDetailDO> stockDetails = new ArrayList<>();

            for (PurchaseInputOrderPutOnItemDTO putOnItem : putOnItems) {
                GoodsAllocationStockDetailDO stockDetail = stockDetailDAO.saveByPutOnItem(
                        putOnItem.clone(PurchaseInputOrderPutOnItemDO.class));
                stockDetails.add(stockDetail);
            }

            item.setStockDetails(ObjectUtils.convertList(stockDetails,
                    GoodsAllocationStockDetailDTO.class));
        }
    }

    /**
     * 设置需要的参数
     */
    @Override
    public void setParameter(Object parameter) {
        this.purchaseInputOrder = (PurchaseInputOrderDTO) parameter;
    }

}
