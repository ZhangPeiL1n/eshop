package com.zpl.eshop.membership.dao;

import com.zpl.eshop.membership.domain.DeliveryAddressDO;

import java.util.List;

/**
 * 收货地址管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface DeliveryAddressDAO {

    /**
     * 查询用户账号的所有收货地址
     *
     * @param userAccountId 用户账号id
     * @return 所有收货地址
     * @throws Exception
     */
    List<DeliveryAddressDO> listAllByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 新增收货地址
     *
     * @param deliveryAddress 收货地址
     * @throws Exception
     */
    void save(DeliveryAddressDO deliveryAddress) throws Exception;

    /**
     * 更新收货地址
     *
     * @param deliveryAddress 收货地址
     * @throws Exception
     */
    void update(DeliveryAddressDO deliveryAddress) throws Exception;

    /**
     * 删除收货地址
     *
     * @param id 收货地址id
     * @throws Exception
     */
    void remove(Long id) throws Exception;
}
