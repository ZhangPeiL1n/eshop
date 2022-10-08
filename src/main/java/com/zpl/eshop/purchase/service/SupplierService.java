package com.zpl.eshop.purchase.service;

import com.zpl.eshop.purchase.domain.SupplierDTO;
import com.zpl.eshop.purchase.domain.SupplierQuery;

import java.util.List;

/**
 * 供应商管理Service接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:38
 **/
public interface SupplierService {

    /**
     * 新增供应商
     *
     * @param supplier 供应商
     */
    void save(SupplierDTO supplier) throws Exception;

    /**
     * 分页查询供应商
     *
     * @param query 供应商查询条件
     * @return 供应商
     */
    List<SupplierDTO> listByPage(SupplierQuery query) throws Exception;

    /**
     * 根据id查询供应商
     *
     * @param id 供应商id
     * @return 供应商
     */
    SupplierDTO getById(Long id) throws Exception;
}
