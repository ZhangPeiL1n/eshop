package com.zpl.eshop.customer.controller;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetQuery;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetVO;
import com.zpl.eshop.customer.service.ReturnGoodsWorksheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 客服中心退货工单管理Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 22:20
 **/
@RestController
@RequestMapping("/customer/return/goods/worksheet")
public class ReturnGoodsWorksheetController {

    private static final Logger logger = LoggerFactory.getLogger(ReturnGoodsWorksheetController.class);

    /**
     * 退货工单管理service组件
     */
    @Autowired
    private ReturnGoodsWorksheetService returnGoodsWorksheetService;

    /**
     * 分页查询退货工单
     *
     * @param query 查询条件
     * @return 退货工单
     */
    @GetMapping("/")
    public List<ReturnGoodsWorksheetVO> listByPage(ReturnGoodsWorksheetQuery query) {
        try {
            return ObjectUtils.convertList(
                    returnGoodsWorksheetService.listByPage(query),
                    ReturnGoodsWorksheetVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询退货工单
     *
     * @param id 退货工单id
     * @return 退货工单
     */
    @GetMapping("/{id}")
    public ReturnGoodsWorksheetVO getById(@PathVariable("id") Long id) {
        try {
            return returnGoodsWorksheetService.getById(id)
                    .clone(ReturnGoodsWorksheetVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ReturnGoodsWorksheetVO();
        }
    }
}
