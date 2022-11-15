package com.zpl.eshop.schedule.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDetailDO;
import com.zpl.eshop.schedule.mapper.ScheduleGoodsAllocationStockDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调度中心货位库存明细DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/4 17:30
 **/
@Repository
public class ScheduleGoodsAllocationStockDetailDAOImpl implements ScheduleGoodsAllocationStockDetailDAO {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 调度中心货位库存明细Mapper组件
     */
    @Autowired
    private ScheduleGoodsAllocationStockDetailMapper stockDetailMapper;

    /**
     * 根据商品sku id查询货位库存明细
     *
     * @param goodsSkuId 商品sku id
     * @return 货位库存明细
     * @throws Exception
     */
    @Override
    public List<ScheduleGoodsAllocationStockDetailDO> listByGoodsSkuId(Long goodsSkuId) throws Exception {
        return stockDetailMapper.listByGoodsSkuId(goodsSkuId);
    }

    /**
     * 根据id查询货位库存明细
     *
     * @param id 货位库粗明细id
     * @return 货位库存明细
     */
    @Override
    public ScheduleGoodsAllocationStockDetailDO getById(Long id) {
        return stockDetailMapper.getById(id);
    }

    /**
     * 更新货位库存明细
     *
     * @param stockDetail 货位库存明细
     * @throws Exception
     */
    @Override
    public void update(ScheduleGoodsAllocationStockDetailDO stockDetail) throws Exception {
        stockDetail.setGmtModified(dateProvider.getCurrentTime());
        stockDetailMapper.update(stockDetail);
    }

    /**
     * 新增货位库存明细
     *
     * @param stockDetail 货位库存明细
     * @throws Exception
     */
    @Override
    public void save(ScheduleGoodsAllocationStockDetailDO stockDetail) throws Exception {
        stockDetail.setGmtCreate(dateProvider.getCurrentTime());
        stockDetail.setGmtModified(dateProvider.getCurrentTime());
        stockDetailMapper.save(stockDetail);
    }
}
