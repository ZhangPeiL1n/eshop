package com.zpl.eshop.order.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.ReturnGoodsApplyStatus;
import com.zpl.eshop.order.dao.ReturnGoodsApplyDAO;
import com.zpl.eshop.order.domain.ReturnGoodsApplyDO;
import com.zpl.eshop.order.mapper.ReturnGoodsApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 退货申请DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/15 19:53
 **/
@Repository
public class ReturnGoodsApplyDAOImpl implements ReturnGoodsApplyDAO {

    /**
     * 退货申请mapper
     */
    @Autowired
    private ReturnGoodsApplyMapper returnGoodsApplyMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增退货申请
     *
     * @param apply 退货申请
     * @throws Exception
     */
    @Override
    public void save(ReturnGoodsApplyDO apply) throws Exception {
        apply.setReturnGoodsApplyStatus(ReturnGoodsApplyStatus.WAIT_FOR_APPROVE);
        apply.setGmtCreate(dateProvider.getCurrentTime());
        apply.setGmtModified(dateProvider.getCurrentTime());
        returnGoodsApplyMapper.save(apply);
    }

    /**
     * 根据id查询退货申请
     *
     * @param orderInfoId 订单id
     * @return 退货申请
     * @throws Exception
     */
    @Override
    public ReturnGoodsApplyDO getByOrderInfoId(Long orderInfoId) throws Exception {
        return returnGoodsApplyMapper.getByOrderInfoId(orderInfoId);
    }

    /**
     * 更新退货申请
     *
     * @param apply 退货申请
     * @throws Exception
     */
    @Override
    public void update(ReturnGoodsApplyDO apply) throws Exception {
        apply.setGmtModified(dateProvider.getCurrentTime());
        returnGoodsApplyMapper.update(apply);
    }

    /**
     * 更新退货申请的状态
     *
     * @param orderInfoId 订单id
     * @param status      退货申请状态
     * @throws Exception
     */
    @Override
    public void updateStatus(Long orderInfoId, Integer status) throws Exception {
        ReturnGoodsApplyDO apply = getByOrderInfoId(orderInfoId);
        apply.setReturnGoodsApplyStatus(status);
        apply.setGmtModified(dateProvider.getCurrentTime());
        returnGoodsApplyMapper.update(apply);
    }
}
