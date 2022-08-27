package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.service.GoodsDetailPictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情图片管理Controller
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 17:51
 **/
@RestController
@RequestMapping("/commodity/goods/detail/picture")
public class GoodsDetailPictureController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsDetailPictureController.class);

    /**
     * 商品详情管理Service组件
     */
    @Autowired
    private GoodsDetailPictureService goodsDetailPictureService;

    /**
     * 批量上传图片
     *
     * @param goodsDetailId 商品详情id
     * @param pictures      图片
     * @return 图片id
     */
    @PostMapping("/{goodsDetailId}")
    public List<Long> batchUploadPicture(@PathVariable("goodsDetailId") Long goodsDetailId, MultipartFile[] pictures) {
        try {
            return goodsDetailPictureService.batchUploadPicture(goodsDetailId, pictures);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

}
