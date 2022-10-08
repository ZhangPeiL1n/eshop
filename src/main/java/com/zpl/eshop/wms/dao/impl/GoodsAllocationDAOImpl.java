package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.GoodsAllocationDAO;
import com.zpl.eshop.wms.domain.GoodsAllocationDO;
import com.zpl.eshop.wms.domain.GoodsAllocationQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 货位管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 14:04
 **/
@Repository
public class GoodsAllocationDAOImpl implements GoodsAllocationDAO {

    /**
     * 分页查询货位
     *
     * @param query 查询条件
     * @return 货位
     */
    @Override
    public List<GoodsAllocationDO> listByPage(GoodsAllocationQuery query) {
        return null;
    }

    /**
     * 新增货位
     *
     * @param goodsAllocation 货位
     */
    @Override
    public void save(GoodsAllocationDO goodsAllocation) {

    }

    /**
     * 根据id查询货位
     *
     * @param id 货位id
     * @return 货位
     */
    @Override
    public GoodsAllocationDO getById(Long id) {
        return null;
    }

    /**
     * 更新货位
     *
     * @param goodsAllocation 货位
     */
    @Override
    public void update(GoodsAllocationDO goodsAllocation) {

    }
}
