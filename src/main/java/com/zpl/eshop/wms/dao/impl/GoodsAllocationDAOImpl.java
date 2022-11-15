package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.GoodsAllocationDAO;
import com.zpl.eshop.wms.domain.GoodsAllocationDO;
import com.zpl.eshop.wms.domain.GoodsAllocationQuery;
import com.zpl.eshop.wms.mapper.GoodsAllocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 货位管理mapper组件
     */
    @Autowired
    private GoodsAllocationMapper goodsAllocationMapper;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询货位
     *
     * @param query 查询条件
     * @return 货位
     * @throws Exception
     */
    @Override
    public List<GoodsAllocationDO> listByPage(GoodsAllocationQuery query) throws Exception {
        return goodsAllocationMapper.listByPage(query);
    }

    /**
     * 新增货位
     *
     * @param goodsAllocation 货位
     * @throws Exception
     */
    @Override
    public void save(GoodsAllocationDO goodsAllocation) throws Exception {
        goodsAllocation.setGmtCreate(dateProvider.getCurrentTime());
        goodsAllocation.setGmtModified(dateProvider.getCurrentTime());
        goodsAllocationMapper.save(goodsAllocation);
    }

    /**
     * 根据id查询货位
     *
     * @param id 货位id
     * @return 货位
     * @throws Exception
     */
    @Override
    public GoodsAllocationDO getById(Long id) throws Exception {
        return goodsAllocationMapper.getById(id);
    }

    /**
     * 更新货位
     *
     * @param goodsAllocation 货位
     * @throws Exception
     */
    @Override
    public void update(GoodsAllocationDO goodsAllocation) throws Exception {
        goodsAllocation.setGmtModified(dateProvider.getCurrentTime());
        goodsAllocationMapper.update(goodsAllocation);
    }
}
