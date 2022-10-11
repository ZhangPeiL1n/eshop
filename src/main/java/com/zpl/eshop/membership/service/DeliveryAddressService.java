package com.zpl.eshop.membership.service;

import com.zpl.eshop.membership.domain.DeliveryAddressDTO;

import java.util.List;

/**
 * 收货地址管理service接口
 *
 * @author ZhangPeiL1n
 */
public interface DeliveryAddressService {

    /**
     * 查询用户账号的所有收货地址
     *
     * @param userAccountId 用户账号id
     * @return 所有收货地址
     */
    List<DeliveryAddressDTO> listAllByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 新增收货地址
     *
     * @param deliveryAddress 收货地址
     */
    void save(DeliveryAddressDTO deliveryAddress) throws Exception;

    /**
     * 更新收货地址
     *
     * @param deliveryAddress 收货地址
     */
    void update(DeliveryAddressDTO deliveryAddress) throws Exception;

    /**
     * 删除收货地址
     *
     * @param id 收货地址id
     */
    void remove(Long id) throws Exception;

}
