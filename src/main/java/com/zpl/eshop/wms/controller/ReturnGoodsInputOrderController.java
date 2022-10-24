package com.zpl.eshop.wms.controller;

import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderQuery;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderVO;
import com.zpl.eshop.wms.service.ReturnGoodsInputOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 退货入库单管理Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/24 22:37
 **/
@RestController
@RequestMapping("/wms/returnGoodsInputOrder")
public class ReturnGoodsInputOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ReturnGoodsInputOrderController.class);

    /**
     * 退货入库单管理service组件
     */
    @Autowired
    private ReturnGoodsInputOrderService returnGoodsInputOrderService;

    /**
     * 分页查询退货入库单
     *
     * @param query 查询条件
     * @return 退货入库单
     */
    @GetMapping("/")
    public List<ReturnGoodsInputOrderVO> listByPage(ReturnGoodsInputOrderQuery query) {
        try {
            return ObjectUtils.convertList(
                    returnGoodsInputOrderService.listByPage(query),
                    ReturnGoodsInputOrderVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查询退货入库单
     *
     * @param id 退货入库单id
     * @return 退货入库单
     * @throws Exception
     */
    @GetMapping("/{id}")
    public ReturnGoodsInputOrderVO getById(@PathVariable("id") Long id) {
        try {
            return returnGoodsInputOrderService.getById(id)
                    .clone(ReturnGoodsInputOrderVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
