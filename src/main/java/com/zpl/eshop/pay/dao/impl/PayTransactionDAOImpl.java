package com.zpl.eshop.pay.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.pay.dao.PayTransactionDAO;
import com.zpl.eshop.pay.domain.PayTransactionDO;
import com.zpl.eshop.pay.mapper.PayTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:19
 **/
@Repository
public class PayTransactionDAOImpl implements PayTransactionDAO {

    /**
     * 支付交易流水管理mapper组件
     */
    @Autowired
    private PayTransactionMapper payTransactionMapper;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增支付交易流水
     *
     * @param payTransaction 支付交易流水
     */
    @Override
    public void save(PayTransactionDO payTransaction) throws Exception {
        payTransaction.setGmtCreate(dateProvider.getCurrentTime());
        payTransaction.setGmtModified(dateProvider.getCurrentTime());
        payTransactionMapper.save(payTransaction);
    }

    /**
     * 根据条件查询支付交易流水
     *
     * @param parameters 查询条件
     * @return 支付交易流水
     * @throws Exception
     */
    @Override
    public List<PayTransactionDO> listByCondition(Map<String, Object> parameters) throws Exception {
        return payTransactionMapper.listByCondition(parameters);
    }

    /**
     * 更新支付交易流水
     *
     * @param payTransaction 支付交易流水
     */
    @Override
    public void update(PayTransactionDO payTransaction) throws Exception {
        payTransaction.setGmtModified(dateProvider.getCurrentTime());
        payTransactionMapper.update(payTransaction);
    }
}
