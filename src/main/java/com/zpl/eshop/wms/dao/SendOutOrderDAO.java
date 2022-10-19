package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.SendOutOrderDO;

/**
 * 发货单管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:21
 **/
public interface SendOutOrderDAO {

    /**
     * 新增发货单
     *
     * @param sendOutOrder 发货单
     */
    Long save(SendOutOrderDO sendOutOrder) throws Exception;

    /**
     * 根据id查询发货单
     *
     * @param id 发货单id
     * @return 发货单
     */
    SendOutOrderDO getBySaleDeliveryOrderId(Long id);
}
