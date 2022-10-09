package com.zpl.eshop.wms.controller;

import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.wms.domain.*;
import com.zpl.eshop.wms.service.PurchaseInputOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * wms中心Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 13:45
 **/
@RestController
@RequestMapping("/wms/purchaseInputOrder/")
public class PurchaseInputOrderController {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseInputOrderController.class);

    /**
     * 采购入库单管理service组件
     */
    @Autowired
    private PurchaseInputOrderService purchaseInputOrderService;

    /**
     * 分页查询采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    @GetMapping("/")
    public List<PurchaseInputOrderVO> listByPage(PurchaseInputOrderQuery query) {
        try {
            return ObjectUtils.convertList(
                    purchaseInputOrderService.listByPage(query),
                    PurchaseInputOrderVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查询采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    @GetMapping("/{id}")
    public PurchaseInputOrderVO getById(@PathVariable("id") Long id) {
        try {
            return purchaseInputOrderService.getById(id).clone(
                    PurchaseInputOrderVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 更新采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     * @throws Exception
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody PurchaseInputOrderVO purchaseInputOrder) {
        try {
            purchaseInputOrderService.update(purchaseInputOrder.clone(
                    PurchaseInputOrderDTO.class, CloneDirection.FORWARD));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 批量新增采购入库单的上架条目
     *
     * @param putOnItems 上架条目
     * @throws Exception
     */
    @PutMapping("/putOnShelves/{id}")
    public Boolean batchSavePutOnItems(List<PurchaseInputOrderPutOnItemVO> putOnItems) {
        try {
            purchaseInputOrderService.batchSavePutOnItems(ObjectUtils.convertList(
                    putOnItems, PurchaseInputOrderPutOnItemDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 对采购入库单提交审核
     *
     * @param id 采购入库单id
     * @throws Exception
     */
    @PutMapping("/submitApprove/{id}")
    public Boolean submitApprove(@PathVariable("id") Long id) throws Exception {
        try {
            purchaseInputOrderService.submitApprove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 对采购入库单进行审核
     *
     * @param id            采购入库单id
     * @param approveResult 审核结果
     * @throws Exception
     */
    @PutMapping("/approve/{id}")
    public Boolean approve(@PathVariable("id") Long id, Integer approveResult) throws Exception {
        try {
            purchaseInputOrderService.approve(id, approveResult);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

}
