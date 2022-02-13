package com.zpl.eshop.inventory.updater;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 退货入库更新库存命令
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 21:16
 **/
public class ReturnInputStockUpdater extends AbstractStockUpdater {

    /**
     * 退货入库单条目DTO集合
     */
    private Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap;

    /**
     * 构造函数
     *
     * @param goodsStockDOList 商品库存对象集合
     * @param goodsStockDAO    商品库存管理模块DAO组件
     * @param dateProvider     日期辅助组件
     */
    public ReturnInputStockUpdater(
            List<GoodsStockDO> goodsStockDOList,
            GoodsStockDAO goodsStockDAO,
            DateProvider dateProvider,
            Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap) {
        super(goodsStockDOList, goodsStockDAO, dateProvider);
        this.returnGoodsInputOrderItemDTOMap = returnGoodsInputOrderItemDTOMap;
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
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getId());
            // 设置入库后的库存
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() + returnGoodsInputOrderItemDTO.getArrivalCount());
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
    protected void updateSaledStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            // 获取采购条目DTO对象
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getId());
            // 设置入库后的库存
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaledStockQuantity() - returnGoodsInputOrderItemDTO.getArrivalCount());
        }
    }
}
