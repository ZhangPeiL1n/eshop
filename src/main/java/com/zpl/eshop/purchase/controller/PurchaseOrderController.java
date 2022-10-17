package com.zpl.eshop.purchase.controller;

import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderQuery;
import com.zpl.eshop.purchase.domain.PurchaseOrderVO;
import com.zpl.eshop.purchase.service.PurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购单管理controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/17 22:36
 **/
@RestController
@RequestMapping("/purchase/order")
public class PurchaseOrderController {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

    /**
     * 采购单管理service组件
     */
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    /**
     * 分页查询采购单
     *
     * @return 采购单
     * @throws Exception
     */
    @GetMapping("/")
    public List<PurchaseOrderVO> listByPage(PurchaseOrderQuery query) {
        try {
            return ObjectUtils.convertList(
                    purchaseOrderService.listByPage(query),
                    PurchaseOrderVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查询采购单
     *
     * @return 采购单
     * @throws Exception
     */
    @GetMapping("/{id}")
    public PurchaseOrderVO getById(@PathVariable("id") Long id) {
        try {
            return purchaseOrderService.getById(id).clone(
                    PurchaseOrderVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增采购单
     *
     * @param purchaseOrder 采购单
     * @throws Exception
     */
    @PostMapping("/")
    public Boolean update(@RequestBody PurchaseOrderVO purchaseOrder) {
        try {
            purchaseOrderService.save(purchaseOrder.clone(
                    PurchaseOrderDTO.class, CloneDirection.FORWARD));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

}
