package com.zpl.eshop.inventory.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 采购入库更新销库存命令
 * <p>
 * 采购入库只需要关心采购入库单的中各个商品条目的采购数量就行
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:03
 **/
public class PurchaseInputStockUpdater extends AbstractStockUpdater {

    /**
     * 采购入库单条目DTO集合
     */
    private final Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap;

    /**
     * 构造函数
     *
     * @param goodsStockDO              商品库存对象
     * @param goodsStockDAO             商品库存管理模块DAO组件
     * @param dateProvider              日期辅助组件
     * @param purchaseInputOrderItemMap 采购入库单条目DTO Map
     */
    public PurchaseInputStockUpdater(
            List<GoodsStockDO> goodsStockDO,
            GoodsStockDAO goodsStockDAO,
            DateProvider dateProvider,
            Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemMap) {
        super(goodsStockDO, goodsStockDAO, dateProvider);
        this.purchaseInputOrderItemDTOMap = purchaseInputOrderItemMap;
    }

    /**
     * 更新销售库存
     *
     * @throws Exception
     */
    @Override
    protected void updateSaleStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            // 获取采购条目DTO对象
            PurchaseInputOrderItemDTO purchaseInputOrderItemDTO = purchaseInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            // 设置入库后的库存
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() + purchaseInputOrderItemDTO.getArrivalCount());
        }
    }

    /**
     * 更新锁定库存
     *
     * @throws Exception
     */
    @Override
    protected void updateLockedStockQuantity() throws Exception {

    }

    /**
     * 更新已销售库存
     *
     * @throws Exception
     */
    @Override
    protected void updateSoldStockQuantity() throws Exception {

    }
}
