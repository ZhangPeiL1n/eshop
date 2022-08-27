package com.zpl.eshop.commodity.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品详情图片管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 17:23
 **/
public interface GoodsDetailPictureService {

    /**
     * 批量上传图片
     *
     * @param goodsDetailId 商品详情id
     * @param pictures      图片
     * @return 图片id
     */
    List<Long> batchUploadPicture(Long goodsDetailId, MultipartFile[] pictures);
}
