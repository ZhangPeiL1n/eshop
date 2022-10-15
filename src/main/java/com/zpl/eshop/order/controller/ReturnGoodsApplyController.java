package com.zpl.eshop.order.controller;

import com.zpl.eshop.order.domain.ReturnGoodsApplyVO;
import com.zpl.eshop.order.service.ReturnGoodsApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/15 21:44
 **/
@RestController
@RequestMapping("/order/returnGoodsApply")
public class ReturnGoodsApplyController {
    private static final Logger logger = LoggerFactory.getLogger(ReturnGoodsApplyController.class);

    /**
     * 退货申请管理service组件
     */
    @Autowired
    private ReturnGoodsApplyService returnGoodsApplyService;

    /**
     * 根据订单id查询退货申请
     *
     * @param orderInfoId 订单id
     * @return 退货申请
     * @throws Exception
     */
    @GetMapping("/{orderInfoId}")
    public ReturnGoodsApplyVO getByOrderInfoId(Long orderInfoId) {
        try {
            return returnGoodsApplyService.getByOrderInfoId(orderInfoId)
                    .clone(ReturnGoodsApplyVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
