package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.PurchaseInputOrderPutOnItemDAO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDO;
import com.zpl.eshop.wms.mapper.PurchaseInputOrderPutOnItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购入库单上架条目DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 17:30
 **/
@Repository
public class PurchaseInputOrderPutOnItemDAOImpl implements PurchaseInputOrderPutOnItemDAO {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 采购入库单上架条目Mapper
     */
    @Autowired
    private PurchaseInputOrderPutOnItemMapper purchaseInputOrderPutOnItemMapper;

    /**
     * 批量新增采购入库单上架条目
     *
     * @param putOnItems 上架条目
     * @throws Exception
     */
    @Override
    public void batchSave(List<PurchaseInputOrderPutOnItemDO> putOnItems) throws Exception {
        putOnItems.forEach(item -> {
            try {
                item.setGmtCreate(dateProvider.getCurrentTime());
                item.setGmtModified(dateProvider.getCurrentTime());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            purchaseInputOrderPutOnItemMapper.save(item);
        });
    }


    /**
     * 根据采购入库单条目id查询采购入库单上架条目
     *
     * @param purchaseInputOrderItemId 采购入库单id
     * @return 采购入库单上架条目
     * @throws Exception
     */
    @Override
    public List<PurchaseInputOrderPutOnItemDO> listByPurchaseInputOrderItemId(Long purchaseInputOrderItemId) throws Exception {
        return purchaseInputOrderPutOnItemMapper.listByPurchaseInputOrderItemId(purchaseInputOrderItemId);
    }
}
