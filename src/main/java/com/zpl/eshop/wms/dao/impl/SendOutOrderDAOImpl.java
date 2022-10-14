package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.SendOutOrderDAO;
import com.zpl.eshop.wms.domain.SendOutOrderDO;
import com.zpl.eshop.wms.mapper.SendOutOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 发货单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:22
 **/
@Repository
public class SendOutOrderDAOImpl implements SendOutOrderDAO {

    /**
     * 发货单管理mapper组件
     */
    @Autowired
    private SendOutOrderMapper sendOutOrderMapper;

    /**
     * 新增发货单
     *
     * @param sendOutOrder 发货单
     */
    @Override
    public Long save(SendOutOrderDO sendOutOrder) throws Exception {
        sendOutOrderMapper.save(sendOutOrder);
        return sendOutOrder.getId();
    }
}
