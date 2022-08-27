package com.zpl.eshop.commodity.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 商品图片管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 12:12
 **/
public interface GoodsPictureService {

    /**
     * 批量上传图片
     *
     * @param goodsId  商品id
     * @param pictures 商品图片
     * @throws Exception
     */
    void batchSave(Long goodsId, MultipartFile[] pictures) throws Exception;
}
