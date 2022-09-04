package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.GoodsPictureDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品图片管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 12:12
 **/
public interface GoodsPictureService {

    /**
     * 根据商品id查询商品图片id
     *
     * @param goodsId 商品id
     * @return 商品图片id
     */
    List<Long> listIdsByGoodsId(Long goodsId);

    /**
     * 根据id查询商品图片
     *
     * @param id 商品图片id
     * @return 商品图片
     */
    GoodsPictureDTO getById(Long id) throws Exception;

    /**
     * 批量上传图片
     *
     * @param goodsId  商品id
     * @param pictures 商品图片
     * @throws Exception
     */
    void batchSave(Long goodsId, MultipartFile[] pictures) throws Exception;

    /**
     * 根据商品id删除图片
     *
     * @param goodsId 商品id
     */
    void batchRemoveByGoodsId(Long goodsId);
}
