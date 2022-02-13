package com.zpl.eshop.inventory.updater;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.order.domain.OrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 提交订单库存更新组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/9 22:31
 **/
public class CancelOrderStockUpdater extends AbstractStockUpdater {

    /**
     * 订单条目DTO集合
     */
    private Map<Long, OrderItemDTO> orderItemDTOMap;

    /**
     * 构造函数
     *
     * @param goodsStockDOList 商品库存对象集合
     * @param goodsStockDAO    商品库存管理模块DAO组件
     * @param dateProvider     日期辅助组件
     * @param orderItemDTOMap  订单条目DTO集合
     */
    public CancelOrderStockUpdater(List<GoodsStockDO> goodsStockDOList, GoodsStockDAO goodsStockDAO, DateProvider dateProvider, Map<Long, OrderItemDTO> orderItemDTOMap) {
        super(goodsStockDOList, goodsStockDAO, dateProvider);
        this.orderItemDTOMap = orderItemDTOMap;
    }

    /**
     * 更新销售库存
     *
     * @throws Exception
     */
    @Override
    protected void updateSaleStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() + orderItemDTO.getPurchaseQuantity());
        }
    }

    /**
     * 更新锁定库存
     *
     * @throws Exception
     */
    @Override
    protected void updateLockedStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setLockedStockQuantity(goodsStockDO.getLockedStockQuantity() - orderItemDTO.getPurchaseQuantity());
        }
    }

    /**
     * 更新已销售库存
     *
     * @throws Exception
     */
    @Override
    protected void updateSaledStockQuantity() throws Exception {
    }
}
