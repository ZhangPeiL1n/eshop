package com.zpl.eshop.wms.service;

import com.zpl.eshop.wms.domain.GoodsAllocationDTO;
import com.zpl.eshop.wms.domain.GoodsAllocationQuery;

import java.util.List;

/**
 * 货位管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 14:06
 **/
public interface GoodsAllocationService {

    /**
     * 分页查询货位
     *
     * @param query 查询条件
     * @return 货位
     */
    List<GoodsAllocationDTO> listByPage(GoodsAllocationQuery query);

    /**
     * 新增货位
     *
     * @param goodsAllocation 货位
     */
    void save(GoodsAllocationDTO goodsAllocation);

    /**
     * 根据id查询货位
     *
     * @param id 货位id
     * @return 货位
     */
    GoodsAllocationDTO getById(Long id);

    /**
     * 更新货位
     *
     * @param goodsAllocation 货位
     */
    void update(GoodsAllocationDTO goodsAllocation);
}
