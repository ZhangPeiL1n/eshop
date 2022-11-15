package com.zpl.eshop.membership.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.dao.DeliveryAddressDAO;
import com.zpl.eshop.membership.domain.DeliveryAddressDO;
import com.zpl.eshop.membership.mapper.DeliveryAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收货地址管理DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class DeliveryAddressDAOImpl implements DeliveryAddressDAO {

    /**
     * 收货地址管理mapper组件
     */
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 查询用户账号的所有收货地址
     *
     * @param userAccountId 用户账号id
     * @return 所有收货地址
     * @throws Exception
     */

    @Override
    public List<DeliveryAddressDO> listAllByUserAccountId(Long userAccountId) throws Exception {
        return deliveryAddressMapper.listAllByUserAccountId(userAccountId);
    }

    /**
     * 新增收货地址
     *
     * @param deliveryAddress 收货地址
     * @throws Exception
     */
    @Override
    public void save(DeliveryAddressDO deliveryAddress) throws Exception {
        deliveryAddress.setGmtCreate(dateProvider.getCurrentTime());
        deliveryAddress.setGmtModified(dateProvider.getCurrentTime());
        deliveryAddressMapper.save(deliveryAddress);
    }

    /**
     * 更新收货地址
     *
     * @param deliveryAddress 收货地址
     * @throws Exception
     */
    @Override
    public void update(DeliveryAddressDO deliveryAddress) throws Exception {
        deliveryAddress.setGmtModified(dateProvider.getCurrentTime());
        deliveryAddressMapper.update(deliveryAddress);
    }

    /**
     * 删除收货地址
     *
     * @param id 收货地址id
     * @throws Exception
     */
    @Override
    public void remove(Long id) throws Exception {
        deliveryAddressMapper.remove(id);
    }
}
