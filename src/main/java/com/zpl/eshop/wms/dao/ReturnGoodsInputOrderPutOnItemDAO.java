package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderPutOnItemDO;

import java.util.List;

/**
 * 退货入库单上架条目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/24 22:31
 **/
public interface ReturnGoodsInputOrderPutOnItemDAO {

    /**
     * 根据退货入库单条目id查询上架条目
     *
     * @param returnGoodsInputOrderItemId 退货入库单条目id
     * @return 上架条目
     * @throws Exception
     */
    List<ReturnGoodsInputOrderPutOnItemDO> listByReturnGoodsInputOrderItemId(
            Long returnGoodsInputOrderItemId) throws Exception;

    /**
     * 新增退货入库单上架条目
     *
     * @param putOnItem 上架条目
     * @throws Exception
     */
    void save(ReturnGoodsInputOrderPutOnItemDO putOnItem) throws Exception;

}
