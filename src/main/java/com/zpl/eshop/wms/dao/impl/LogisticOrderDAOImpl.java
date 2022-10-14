package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.LogisticOrderDAO;
import com.zpl.eshop.wms.domain.LogisticOrderDO;
import com.zpl.eshop.wms.mapper.LogisticOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 物流单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:25
 **/
@Repository
public class LogisticOrderDAOImpl implements LogisticOrderDAO {

    /**
     * 物流单管理mapper组件
     */
    @Autowired
    private LogisticOrderMapper logisticOrderMapper;

    /**
     * 新增物流单
     *
     * @param logisticOrder 物流单
     */
    @Override
    public void save(LogisticOrderDO logisticOrder) throws Exception {
        logisticOrderMapper.save(logisticOrder);
    }

}
