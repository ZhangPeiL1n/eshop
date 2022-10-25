package com.zpl.eshop.order.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoQuery;
import com.zpl.eshop.order.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单管理模块 DAO 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 17:15
 **/
@Repository
public class OrderInfoDAOImpl implements OrderInfoDAO {

    /**
     * 订单管理模块 mapper 组件
     */
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新建订单
     *
     * @param order 订单
     */
    @Override
    public Long save(OrderInfoDO order) throws Exception {
        order.setGmtCreate(dateProvider.getCurrentTime());
        order.setGmtModified(dateProvider.getCurrentTime());
        orderInfoMapper.save(order);
        return order.getId();
    }

    /**
     * 分页查询订单
     *
     * @param query 查询条件
     * @return 订单
     */
    @Override
    public List<OrderInfoDO> listByPage(OrderInfoQuery query) {
        return orderInfoMapper.listByPage(query);
    }

    /**
     * 查询订单详情
     *
     * @return 订单
     */
    @Override
    public OrderInfoDO getById(Long id) {
        return orderInfoMapper.getById(id);
    }

    /**
     * 更新订单状态
     *
     * @param order 订单
     */
    @Override
    public void update(OrderInfoDO order) throws Exception {
        order.setGmtModified(dateProvider.getCurrentTime());
        orderInfoMapper.update(order);
    }

    /**
     * 更新订单状态
     *
     * @param id     订单id
     * @param status 订单状态
     */
    @Override
    public void updateStatus(Long id, Integer status) throws Exception {
        OrderInfoDO order = getById(id);
        order.setOrderStatus(status);
        update(order);
    }

    /**
     * 查询所有未付款的订单
     *
     * @return 所有未付款的订单
     */
    @Override
    public List<OrderInfoDO> listAllUnpaid() {
        return orderInfoMapper.listByStatus(OrderStatus.WAIT_FOR_PAY);
    }

    /**
     * 查询待收货的订单
     *
     * @return 订单
     * @throws Exception
     */
    @Override
    public List<OrderInfoDO> listUnreceived() throws Exception {
        return orderInfoMapper.listByStatus(OrderStatus.WAIT_FOR_RECEIVE);
    }

    /**
     * 查询确认收货时间超过了7天而且还没有发表评论的订单
     *
     * @return 订单
     */
    @Override
    public List<OrderInfoDO> listNotPublishedCommentOrders() {
        return orderInfoMapper.listNotPublishedCommentOrders();
    }
}
