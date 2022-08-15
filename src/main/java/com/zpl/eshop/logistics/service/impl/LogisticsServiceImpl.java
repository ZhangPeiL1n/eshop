package com.zpl.eshop.logistics.service.impl;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.logistics.service.LogisticsService;
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
    public Double calculateFreight(GoodsSkuDTO goodsSkuDTO) {
        return 5.5;
    }
}
