package com.zpl.eshop.wms.mapper;

import com.zpl.eshop.wms.domain.GoodsAllocationDO;
import com.zpl.eshop.wms.domain.GoodsAllocationQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 货位管理Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 14:00
 **/
@Mapper
public interface GoodsAllocationMapper {

    /**
     * 分页查询货位
     *
     * @param query 查询条件
     * @return 货位
     */
    List<GoodsAllocationDO> listByPage(GoodsAllocationQuery query);

    /**
     * 新增货位
     *
     * @param goodsAllocation 货位
     */
    void save(GoodsAllocationDO goodsAllocation);

    /**
     * 根据id查询货位
     *
     * @param id 货位id
     * @return 货位
     */
    GoodsAllocationDO getById(Long id);

    /**
     * 更新货位
     *
     * @param goodsAllocation 货位
     */
    void update(GoodsAllocationDO goodsAllocation);
}
