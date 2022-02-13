package com.zpl.eshop.inventory.updater;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付订单更新库存命令工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/2/9 22:45
 **/
@Component
public class PayOrderStockUpdaterFactory extends AbstractStockUpdaterFactory<OrderInfoDTO> {
    /**
     * 构造函数
     *
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    @Autowired
    public PayOrderStockUpdaterFactory(GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, dateProvider);
    }

    /**
     * 获取要更新库存的所有商品skuId的集合
     *
     * @param parameter 库存来源单：采购入库单或者退货入库单
     * @return 商品SkuId集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(OrderInfoDTO parameter) throws Exception {
        List<Long> goodsSkuIds = new ArrayList<>();
        List<OrderItemDTO> orderItemDTOList = parameter.getOrderItems();
        orderItemDTOList.forEach(orderItemDTO -> goodsSkuIds.add(orderItemDTO.getGoodsSkuId()));
        return goodsSkuIds;
    }

    /**
     * 创建商品库存更新组件
     *
     * @param goodsStockDOList 商品库存对象DO集合
     * @param parameter        库存来源单：采购入库单或者退货入库单
     * @return 商品库存更新组件
     * @throws Exception
     */
    @Override
    protected StockUpdater create(List<GoodsStockDO> goodsStockDOList, OrderInfoDTO parameter) throws Exception {
        List<OrderItemDTO> orderItemDTOList = parameter.getOrderItems();
        Map<Long, OrderItemDTO> orderItemDTOMap = new HashMap<>(orderItemDTOList.size());
        orderItemDTOList.forEach(orderItemDTO -> orderItemDTOMap.put(orderItemDTO.getGoodsSkuId(), orderItemDTO));
        return new PayOrderStockUpdater(goodsStockDOList, goodsStockDAO, dateProvider, orderItemDTOMap);
    }

}
