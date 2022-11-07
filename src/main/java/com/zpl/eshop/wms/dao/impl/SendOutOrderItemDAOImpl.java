package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.SendOutOrderItemDAO;
import com.zpl.eshop.wms.domain.SendOutOrderItemDO;
import com.zpl.eshop.wms.mapper.SendOutOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增发货单条目
     *
     * @param sendOutOrderItem 发货单条目
     * @throws Exception
     */
    @Override
    public void save(SendOutOrderItemDO sendOutOrderItem) throws Exception {
        sendOutOrderItem.setGmtCreate(dateProvider.getCurrentTime());
        sendOutOrderItem.setGmtModified(dateProvider.getCurrentTime());
        sendOutOrderItemMapper.save(sendOutOrderItem);
    }

    /**
     * 查询发货单条目
     *
     * @param sendOutOrderId 发货单id
     * @return 发货单条目
     * @throws Exception
     */

    @Override
    public List<SendOutOrderItemDO> listByOrderInfoId(Long sendOutOrderId) throws Exception {
        return sendOutOrderItemMapper.listByOrderInfoId(sendOutOrderId);
    }
}
