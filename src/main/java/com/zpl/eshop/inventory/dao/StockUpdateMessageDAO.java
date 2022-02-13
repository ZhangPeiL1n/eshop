package com.zpl.eshop.inventory.dao;

import com.zpl.eshop.inventory.domain.StockUpdateMessageDO;

import java.util.List;

/**
 * 库存更新消息DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 22:51
 **/
public interface StockUpdateMessageDAO {

    /**
     * 保存库存更新消息
     *
     * @param stockUpdateMessageDO 库存更新消息DO对象
     * @return ture表示新增成功
     */
    Boolean save(StockUpdateMessageDO stockUpdateMessageDO);

    /**
     * 离线恢复线程使用，每次批量查询50条
     *
     * @return 50条数据
     */
    List<StockUpdateMessageDO> listByBatch();

    /**
     * 批量删除
     *
     * @param ids id
     * @return true 删除成功
     */
    Boolean removeByBatch(String ids);

    /**
     * 查询库存消息表的记录数
     *
     * @return 记录数
     */
    Long count();
}
