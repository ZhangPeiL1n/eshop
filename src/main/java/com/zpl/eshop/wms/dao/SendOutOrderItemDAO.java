package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.SendOutOrderItemDO;

import java.util.List;

/**
 * 发货单条目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:23
 **/
public interface SendOutOrderItemDAO {

    /**
     * 新增发货单条目
     *
     * @param sendOutOrderItem 发货单条目
     */
    void save(SendOutOrderItemDO sendOutOrderItem) throws Exception;

    /**
     * 查询发货单条目
     *
     * @param sendOutOrderId 发货单id
     * @return 发货单条目
     */
    List<SendOutOrderItemDO> listByOrderInfoId(Long sendOutOrderId);
}
