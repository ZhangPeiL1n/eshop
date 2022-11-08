package com.zpl.eshop.order.dao;

import com.zpl.eshop.order.domain.ReturnGoodsApplyDO;

/**
 * 退货申请DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/15 19:51
 **/
public interface ReturnGoodsApplyDAO {

    /**
     * 新增退货申请
     *
     * @param apply 退货申请
     * @throws Exception
     */
    void save(ReturnGoodsApplyDO apply) throws Exception;

    /**
     * 根据id查询退货申请
     *
     * @param orderInfoId 订单id
     * @return 退货申请
     * @throws Exception
     */
    ReturnGoodsApplyDO getByOrderInfoId(Long orderInfoId) throws Exception;

    /**
     * 更新退货申请
     *
     * @param apply 退货申请
     * @throws Exception
     */
    void update(ReturnGoodsApplyDO apply) throws Exception;

    /**
     * 更新退货申请的状态
     *
     * @param orderInfoId 订单id
     * @param status      退货申请状态
     * @throws Exception
     */
    void updateStatus(Long orderInfoId, Integer status) throws Exception;
}
