package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.SendOutOrderItemDAO;
import com.zpl.eshop.wms.domain.SendOutOrderItemDO;
import com.zpl.eshop.wms.mapper.SendOutOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 发货单条目管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:23
 **/
@Repository
public class SendOutOrderItemDAOImpl implements SendOutOrderItemDAO {

    /**
     * 发货单条目管理mapper组件
     */
    @Autowired
    private SendOutOrderItemMapper sendOutOrderItemMapper;

    /**
     * 新增发货单条目
     *
     * @param sendOutOrderItem 发货单条目
     */
    @Override
    public void save(SendOutOrderItemDO sendOutOrderItem) throws Exception {
        sendOutOrderItemMapper.save(sendOutOrderItem);
    }
}
