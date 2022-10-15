package com.zpl.eshop.order.service;

import com.zpl.eshop.order.domain.ReturnGoodsApplyDTO;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/15 21:45
 **/
public interface ReturnGoodsApplyService {

    /**
     * 根据订单id查询退货申请
     *
     * @param orderInfoId 订单id
     * @return 退货申请
     * @throws Exception
     */
    ReturnGoodsApplyDTO getByOrderInfoId(Long orderInfoId) throws Exception;
}
