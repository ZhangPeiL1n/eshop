package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.wms.domain.GoodsAllocationDTO;
import com.zpl.eshop.wms.domain.GoodsAllocationQuery;
import com.zpl.eshop.wms.service.GoodsAllocationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 货位管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 14:07
 **/
@Service
public class GoodsAllocationServiceImpl implements GoodsAllocationService {

    /**
     * 分页查询货位
     *
     * @param query 查询条件
     * @return 货位
     */
    @Override
    public List<GoodsAllocationDTO> listByPage(GoodsAllocationQuery query) {
        return null;
    }

    /**
     * 新增货位
     *
     * @param goodsAllocation 货位
     */
    @Override
    public void save(GoodsAllocationDTO goodsAllocation) {

    }

    /**
     * 根据id查询货位
     *
     * @param id 货位id
     * @return 货位
     */
    @Override
    public GoodsAllocationDTO getById(Long id) {
        return null;
    }

    /**
     * 更新货位
     *
     * @param goodsAllocation 货位
     */
    @Override
    public void update(GoodsAllocationDTO goodsAllocation) {

    }
}
