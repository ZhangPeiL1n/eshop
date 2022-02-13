package com.zpl.eshop.inventory.dao.impl;

import com.zpl.eshop.inventory.dao.StockUpdateMessageDAO;
import com.zpl.eshop.inventory.domain.StockUpdateMessageDO;
import com.zpl.eshop.inventory.mapper.StockUpdateMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 库存更新消息管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 22:54
 **/
@Repository
public class StockUpdateMessageDAOImpl implements StockUpdateMessageDAO {

    private final Logger logger = LoggerFactory.getLogger(StockUpdateMessageDAOImpl.class);

    /**
     * 库存更新消息管理Mapper组件
     */
    @Autowired
    private StockUpdateMessageMapper stockUpdateMessageMapper;

    /**
     * 保存库存更新消息
     *
     * @param stockUpdateMessageDO 库存更新消息DO对象
     */
    @Override
    public Boolean save(StockUpdateMessageDO stockUpdateMessageDO) {
        try {
            stockUpdateMessageMapper.save(stockUpdateMessageDO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 离线恢复线程使用，每次批量查询50条
     *
     * @return 50条数据
     */
    @Override
    public List<StockUpdateMessageDO> listByBatch() {
        try {
            List<StockUpdateMessageDO> stockUpdateMessageDOList = stockUpdateMessageMapper.listByBatch();
            return stockUpdateMessageDOList;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 批量删除
     *
     * @param ids id
     */
    @Override
    public Boolean removeByBatch(String ids) {
        try {
            stockUpdateMessageMapper.removeByBatch(ids);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 查询库存消息表的记录数
     *
     * @return 记录数
     */
    @Override
    public Long count() {
        try {
            return stockUpdateMessageMapper.count();
        } catch (Exception e) {
            logger.error("error", e);
            return 0L;
        }
    }
}
