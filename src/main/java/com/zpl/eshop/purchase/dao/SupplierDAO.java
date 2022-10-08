package com.zpl.eshop.purchase.dao;

import com.zpl.eshop.purchase.domain.SupplierDO;
import com.zpl.eshop.purchase.domain.SupplierQuery;

import java.util.List;

/**
 * 供应商管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:35
 **/
public interface SupplierDAO {

    /**
     * 新增供应商
     *
     * @param supplier 供应商
     */
    void save(SupplierDO supplier) throws Exception;

    /**
     * 分页查询供应商
     *
     * @param query 供应商查询条件
     * @return 供应商
     */
    List<SupplierDO> listByPage(SupplierQuery query);

    /**
     * 根据id查询供应商
     *
     * @param id 供应商id
     * @return 供应商
     */
    SupplierDO getById(Long id);
}
