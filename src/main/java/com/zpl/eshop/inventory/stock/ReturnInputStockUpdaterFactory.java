package com.zpl.eshop.inventory.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 退货入库库存更新命令工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 22:20
 **/
@Component
public class ReturnInputStockUpdaterFactory extends AbstractStockUpdaterFactory<ReturnGoodsInputOrderDTO> {


    /**
     * 构造函数
     *
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    @Autowired
    public ReturnInputStockUpdaterFactory(GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, dateProvider);
    }

    /**
     * 获取商品skuId集合
     *
     * @return 商品skuId集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(ReturnGoodsInputOrderDTO parameter) throws Exception {
        // 拿到退货入库单条目集合
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOList = parameter.getReturnGoodsInputOrderItems();
        // 卫语句,没有就返回空集合
        if (returnGoodsInputOrderItemDTOList == null || returnGoodsInputOrderItemDTOList.size() == 0) {
            return new ArrayList<>();
        }

        // 构造商品skuId集合
        ArrayList<Long> goodsSkuIds = new ArrayList<>(returnGoodsInputOrderItemDTOList.size());
        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO : returnGoodsInputOrderItemDTOList) {
            goodsSkuIds.add(returnGoodsInputOrderItemDTO.getGoodsSkuId());
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
    protected StockUpdater create(List<GoodsStockDO> goodsStockDOList, ReturnGoodsInputOrderDTO parameter) throws Exception {
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOList = parameter.getReturnGoodsInputOrderItems();

        Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap = new HashMap<>();

        // 卫语句,没有就返回空集合
        if (returnGoodsInputOrderItemDTOList != null && returnGoodsInputOrderItemDTOList.size() > 0) {
            for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO : returnGoodsInputOrderItemDTOList) {
                returnGoodsInputOrderItemDTOMap.put(returnGoodsInputOrderItemDTO.getGoodsSkuId(), returnGoodsInputOrderItemDTO);
            }
        }
        return new ReturnInputStockUpdater(goodsStockDOList, goodsStockDAO, dateProvider, returnGoodsInputOrderItemDTOMap);
    }
}
