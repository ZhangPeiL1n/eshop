package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDO;

import java.util.List;

/**
 * 调度中心货位库存明细DAO接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:31
 **/
public interface GoodsAllocationStockDetailDAO {

    /**
     * 根据商品sku id查询货位库存明细
     *
     * @param goodsSkuId 商品sku id
     * @return 货位库存明细
     */
    List<GoodsAllocationStockDetailDO> listByGoodsSkuId(Long goodsSkuId);

    /**
     * 根据id查询货位库存明细
     *
     * @param id 货位库粗明细id
     * @return 货位库存明细
     */
    GoodsAllocationStockDetailDO getById(Long id);

    /**
     * 更新货位库存明细
     *
     * @param stockDetail 货位库存明细
     */
    void update(GoodsAllocationStockDetailDO stockDetail);

    /**
     * 新增货位库存明细
     *
     * @param stockDetail 货位库存明细
     */
    void save(GoodsAllocationStockDetailDO stockDetail);

    /**
     * 根据上架条目新增一个货位库存明细
     *
     * @param putOnItem 上架条目
     * @throws Exception
     */
    GoodsAllocationStockDetailDO saveByPutOnItem(
            PurchaseInputOrderPutOnItemDO putOnItem) throws Exception;
}
