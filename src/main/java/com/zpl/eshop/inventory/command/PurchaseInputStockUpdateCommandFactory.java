package com.zpl.eshop.inventory.command;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购入库库存更新命令工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 22:20
 **/
@Component
public class PurchaseInputStockUpdateCommandFactory extends AbstractGoodsStockUpdateCommandFactory<PurchaseInputOrderDTO> {


    /**
     * 构造函数
     *
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    @Autowired
    public PurchaseInputStockUpdateCommandFactory(GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, dateProvider);
    }

    /**
     * 获取商品skuId集合
     *
     * @return 商品skuId集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(PurchaseInputOrderDTO parameter) throws Exception {
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOList = parameter.getPurchaseInputOrderItemDTOList();
        // 卫语句,没有就返回空集合
        if (purchaseInputOrderItemDTOList == null || purchaseInputOrderItemDTOList.size() == 0) {
            return new ArrayList<>();
        }

        // 构造商品skuId集合
        ArrayList<Long> goodsSkuIds = new ArrayList<>(purchaseInputOrderItemDTOList.size());
        for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOList) {
            goodsSkuIds.add(purchaseInputOrderItemDTO.getGoodsSkuId());
        }
        return goodsSkuIds;
    }

    /**
     * 创建库存更新命令
     *
     * @param goodsStockDOList 商品库存对象DO集合
     * @return 商品更新命令
     * @throws Exception
     */
    @Override
    protected GoodsStockUpdateCommand create(List<GoodsStockDO> goodsStockDOList, PurchaseInputOrderDTO parameter) throws Exception {
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOList = parameter.getPurchaseInputOrderItemDTOList();

        Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap = new HashMap<>();

        // 卫语句,没有就返回空集合
        if (purchaseInputOrderItemDTOList != null && purchaseInputOrderItemDTOList.size() > 0) {
            for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOList) {
                purchaseInputOrderItemDTOMap.put(purchaseInputOrderItemDTO.getId(), purchaseInputOrderItemDTO);
            }
        }

        return new PurchaseInputStockUpdateCommand(goodsStockDOList, goodsStockDAO, dateProvider, purchaseInputOrderItemDTOMap);
    }

}
