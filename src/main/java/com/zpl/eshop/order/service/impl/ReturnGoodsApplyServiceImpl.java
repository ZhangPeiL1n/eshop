package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.order.dao.ReturnGoodsApplyDAO;
import com.zpl.eshop.order.domain.ReturnGoodsApplyDTO;
import com.zpl.eshop.order.service.ReturnGoodsApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退货申请管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/15 21:46
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsApplyServiceImpl implements ReturnGoodsApplyService {

    /**
     * 退货申请管理DAO组件
     */
    @Autowired
    private ReturnGoodsApplyDAO returnGoodsApplyDAO;

    /**
     * 根据订单id查询退货申请
     *
     * @param orderInfoId 订单id
     * @return 退货申请
     * @throws Exception
     */
    @Override
    public ReturnGoodsApplyDTO getByOrderInfoId(Long orderInfoId) throws Exception {
        return returnGoodsApplyDAO.getByOrderInfoId(orderInfoId).clone(ReturnGoodsApplyDTO.class);
    }
}
