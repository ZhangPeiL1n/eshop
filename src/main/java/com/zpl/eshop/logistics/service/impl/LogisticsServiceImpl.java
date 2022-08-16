package com.zpl.eshop.logistics.service.impl;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.membership.domain.DeliveryAddressDTO;
import org.springframework.stereotype.Service;

/**
 * 物流中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 22:22
 **/
@Service
public class LogisticsServiceImpl implements LogisticsService {
    @Override
    public Double calculateFreight(GoodsSkuDTO goodsSkuDTO, DeliveryAddressDTO deliveryAddress) {
        return 5.5;
    }
}
