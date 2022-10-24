package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.wms.dao.ReturnGoodsInputOrderDAO;
import com.zpl.eshop.wms.dao.ReturnGoodsInputOrderItemDAO;
import com.zpl.eshop.wms.dao.ReturnGoodsInputOrderPutOnItemDAO;
import com.zpl.eshop.wms.domain.*;
import com.zpl.eshop.wms.service.ReturnGoodsInputOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 退货入库管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/24 22:36
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsInputOrderServiceImpl implements ReturnGoodsInputOrderService {

    /**
     * 退货入库单管理DAO组件
     */
    @Autowired
    private ReturnGoodsInputOrderDAO returnGoodsInputOrderDAO;

    /**
     * 退货入库单条目管理DAO组件
     */
    @Autowired
    private ReturnGoodsInputOrderItemDAO returnGoodsInputOrderItemDAO;

    /**
     * 退货入库单上架条目管理DAO组件
     */
    @Autowired
    private ReturnGoodsInputOrderPutOnItemDAO putOnItemDAO;

    /**
     * 新增退货入库单
     *
     * @param returnGoodsInputOrder 退货入库单
     * @throws Exception
     */
    @Override
    public void save(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
        Long returnGoodsInputOrderId = returnGoodsInputOrderDAO.save(
                returnGoodsInputOrder.clone(ReturnGoodsInputOrderDO.class));

        for (ReturnGoodsInputOrderItemDTO item : returnGoodsInputOrder.getItems()) {
            item.setReturnGoodsInputOrderId(returnGoodsInputOrderId);
            returnGoodsInputOrderItemDAO.save(item.clone(ReturnGoodsInputOrderItemDO.class));
        }
    }

    /**
     * 分页查询退货入库单
     *
     * @param query 查询条件
     * @return 退货入库单
     */
    @Override
    public List<ReturnGoodsInputOrderDTO> listByPage(
            ReturnGoodsInputOrderQuery query) throws Exception {
        return ObjectUtils.convertList(
                returnGoodsInputOrderDAO.listByPage(query),
                ReturnGoodsInputOrderDTO.class);
    }

    /**
     * 根据id查询退货入库单
     *
     * @param id 退货入库单id
     * @return 退货入库单
     * @throws Exception
     */
    @Override
    public ReturnGoodsInputOrderDTO getById(Long id) throws Exception {
        ReturnGoodsInputOrderDTO returnGoodsInputOrder = returnGoodsInputOrderDAO.getById(id)
                .clone(ReturnGoodsInputOrderDTO.class);

        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems = ObjectUtils.convertList(
                returnGoodsInputOrderItemDAO.listByReturnGoodsInputOrderId(id),
                ReturnGoodsInputOrderItemDTO.class);
        returnGoodsInputOrder.setItems(returnGoodsInputOrderItems);

        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrderItems) {
            List<ReturnGoodsInputOrderPutOnItemDTO> putOnItems = ObjectUtils.convertList(
                    putOnItemDAO.listByReturnGoodsInputOrderItemId(returnGoodsInputOrderItem.getId()),
                    ReturnGoodsInputOrderPutOnItemDTO.class);
            returnGoodsInputOrderItem.setPutOnItems(putOnItems);
        }

        return returnGoodsInputOrder;
    }

}
