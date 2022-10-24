package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderItemDO;

import java.util.List;

/**
 * 退货入库单条目管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/24 22:31
 **/
public interface ReturnGoodsInputOrderItemDAO {

    /**
     * 新增退货入库单条目
     *
     * @param returnGoodsInputOrderItem 退货入库单条目
     */
    void save(ReturnGoodsInputOrderItemDO returnGoodsInputOrderItem) throws Exception;

    /**
     * 查询退货入库单条目
     *
     * @param returnGoodsInputOrderId 退货入库单id
     * @return 退货入库单条目
     */
    List<ReturnGoodsInputOrderItemDO> listByReturnGoodsInputOrderId(
            Long returnGoodsInputOrderId) throws Exception;

}
