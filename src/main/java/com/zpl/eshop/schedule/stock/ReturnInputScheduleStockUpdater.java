package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDetailDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsStockDO;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderItemDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderPutOnItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 退货入库更新库存命令
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 21:16
 **/
@Component
@Scope("prototype")
public class ReturnInputScheduleStockUpdater extends AbstractScheduleStockUpdater {

    /**
     * 采购入库单
     */
    private ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO;

    /**
     * 商品库存管理的DAO组件
     */
    @Autowired
    private ScheduleGoodsStockDAO goodsStockDAO;

    /**
     * 货位库存管理的DAO组件
     */
    @Autowired
    private ScheduleGoodsAllocationStockDAO goodsAllocationStockDAO;

    /**
     * 货位库存明细管理的DAO组件
     */
    @Autowired
    private ScheduleGoodsAllocationStockDetailDAO stockDetailDAO;

    /**
     * 更新商品库存
     *
     * @throws Exception
     */
    @Override
    protected void updateGoodsStock() throws Exception {
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems = returnGoodsInputOrderDTO.getItems();
        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrderItems) {
            ScheduleGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(returnGoodsInputOrderItem.getGoodsSkuId());
            goodsStock.setAvailableStockQuantity(
                    goodsStock.getAvailableStockQuantity() + returnGoodsInputOrderItem.getArrivalCount());
            goodsStock.setOutputStockQuantity(
                    goodsStock.getOutputStockQuantity() - returnGoodsInputOrderItem.getArrivalCount());
            goodsStockDAO.update(goodsStock);
        }
    }

    /**
     * 更新货位库存
     *
     * @throws Exception
     */
    @Override
    protected void updateGoodsAllocationStock() throws Exception {
        List<ReturnGoodsInputOrderItemDTO> items = returnGoodsInputOrderDTO.getItems();
        for (ReturnGoodsInputOrderItemDTO item : items) {
            for (ReturnGoodsInputOrderPutOnItemDTO putOnItem : item.getPutOnItems()) {
                ScheduleGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO.getBySkuId(
                        putOnItem.getGoodsAllocationId(), putOnItem.getGoodsSkuId());
                goodsAllocationStock.setAvailableStockQuantity(
                        goodsAllocationStock.getAvailableStockQuantity() + putOnItem.getPutOnShelvesCount());
                goodsAllocationStock.setOutputStockQuantity(
                        goodsAllocationStock.getOutputStockQuantity() - putOnItem.getPutOnShelvesCount());
                goodsAllocationStockDAO.update(goodsAllocationStock);
            }
        }

    }

    /**
     * 更新货位库存明细
     */
    @Override
    protected void updateGoodsAllocationStockDetail() throws Exception {
        List<ReturnGoodsInputOrderItemDTO> items = returnGoodsInputOrderDTO.getItems();
        for (ReturnGoodsInputOrderItemDTO item : items) {
            for (GoodsAllocationStockDetailDTO stockDetail : item.getStockDetails()) {
                stockDetailDAO.save(stockDetail.clone(ScheduleGoodsAllocationStockDetailDO.class));
            }

        }
    }

    @Override
    public void setParameter(Object parameter) {
        this.returnGoodsInputOrderDTO = (ReturnGoodsInputOrderDTO) parameter;
    }
}
