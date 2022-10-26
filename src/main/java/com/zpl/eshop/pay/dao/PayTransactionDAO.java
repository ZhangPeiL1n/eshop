package com.zpl.eshop.pay.dao;

import com.zpl.eshop.pay.domain.PayTransactionDO;

import java.util.List;
import java.util.Map;

/**
 * 支付交易流水管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:18
 **/
public interface PayTransactionDAO {

    /**
     * 新增支付交易流水
     *
     * @param payTransaction 支付交易流水
     */
    void save(PayTransactionDO payTransaction) throws Exception;

    /**
     * 根据条件查询支付交易流水
     *
     * @param parameters 查询条件
     * @return 支付交易流水
     * @throws Exception
     */
    List<PayTransactionDO> listByCondition(Map<String, Object> parameters) throws Exception;

    /**
     * 更新支付交易流水
     *
     * @param payTransaction 支付交易流水
     */
    void update(PayTransactionDO payTransaction) throws Exception;
}
