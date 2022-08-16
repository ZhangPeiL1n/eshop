package com.zpl.eshop.membership.controller;

import com.zpl.eshop.membership.domain.DeliveryAddressDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收货地址管理 controller 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 13:31
 **/
@RestController
@RequestMapping("/membership/deliveryAddress")
public class DeliveryAddressController {


    /**
     * 查询指定用户的收货地址
     *
     * @param userAccountId 用户帐号id
     * @return 收货地址
     */
    @GetMapping("/user/{userAccountId}")
    public List<DeliveryAddressDO> listByUserAccountId(@PathVariable("userAccountId") Long userAccountId) {
        return null;
    }

}
