package com.zpl.eshop.purchase.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.purchase.dao.SupplierDAO;
import com.zpl.eshop.purchase.domain.SupplierDO;
import com.zpl.eshop.purchase.domain.SupplierQuery;
import com.zpl.eshop.purchase.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 供应商管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:36
 **/
@Repository
public class SupplierDAOImpl implements SupplierDAO {

    /**
     * 供应商管理Mapper
     */
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增供应商
     *
     * @param supplier 供应商
     */
    @Override
    public void save(SupplierDO supplier) throws Exception {
        supplier.setGmtCreate(dateProvider.getCurrentTime());
        supplier.setGmtModified(dateProvider.getCurrentTime());
        supplierMapper.save(supplier);
    }

    /**
     * 分页查询供应商
     *
     * @param query 供应商查询条件
     * @return 供应商
     */
    @Override
    public List<SupplierDO> listByPage(SupplierQuery query) {
        return supplierMapper.listByPage(query);
    }

    /**
     * 根据id查询供应商
     *
     * @param id 供应商id
     * @return 供应商
     */
    @Override
    public SupplierDO getById(Long id) {
        return supplierMapper.getById(id);
    }

    /**
     * 根据id查询供应商
     *
     * @param settlementPeriod 结算周期
     * @return 供应商
     */
    @Override
    public List<SupplierDO> listBySettlementPeriod(Integer settlementPeriod) {
        return supplierMapper.listBySettlementPeriod(settlementPeriod);
    }
}
