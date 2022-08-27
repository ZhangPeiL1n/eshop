package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.service.GoodsPictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品图片管理Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 17:13
 **/
@RestController
@RequestMapping("/commodity/goods/picture")
public class GoodsPictureController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsPictureController.class);

    /**
     * 商品图片管理Service组件
     */
    @Autowired
    private GoodsPictureService goodsPictureService;

    /**
     * 批量上传图片
     *
     * @param goodsId  商品id
     * @param pictures 图片
     * @return 处理结果
     * @throws Exception
     */
    @PostMapping("/{goodsId}")
    public Boolean batchSave(@PathVariable("goodsId") Long goodsId, MultipartFile[] pictures) throws Exception {
        try {
            goodsPictureService.batchSave(goodsId, pictures);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

}
