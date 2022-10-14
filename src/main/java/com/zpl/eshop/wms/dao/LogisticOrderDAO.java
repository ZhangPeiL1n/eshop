package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.LogisticOrderDO;

/**
 * 物流单管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:24
 **/
public interface LogisticOrderDAO {

    /**
     * 新增物流单
     *
     * @param logisticOrder 物流单
     */
    void save(LogisticOrderDO logisticOrder) throws Exception;

}
