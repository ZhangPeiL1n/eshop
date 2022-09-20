package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.GoodsDetailPictureDAO;
import com.zpl.eshop.commodity.domain.GoodsDetailPictureDO;
import com.zpl.eshop.commodity.domain.GoodsDetailPictureDTO;
import com.zpl.eshop.commodity.service.GoodsDetailPictureService;
import com.zpl.eshop.common.constant.PathType;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商品详情图片管理Service
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 17:31
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsDetailPictureServiceImpl implements GoodsDetailPictureService {

    /**
     * 商品详情图片管理DAO组件
     */
    @Autowired
    private GoodsDetailPictureDAO goodsDetailPictureDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 上传图片路径
     */
    @Value("${commodity.goods.image.upload.detail.picture.path｝")
    private String uploadPath;

    /**
     * 上传图片类型
     */
    @Value("${commodity.goods.image.upload.detail.picture.path.type}")
    private String uploadPathType;

    /**
     * 根据id查询商品图片
     *
     * @param id 商品图片id
     * @return 商品图片
     */
    @Override
    public GoodsDetailPictureDTO getById(Long id) throws Exception {
        return goodsDetailPictureDAO.getById(id).clone(GoodsDetailPictureDTO.class);
    }

    /**
     * 批量上传图片
     *
     * @param goodsDetailId 商品详情id
     * @param pictures      图片
     * @return 图片id
     */
    @Override
    public List<Long> batchUploadPicture(Long goodsDetailId, MultipartFile[] pictures) {
        ArrayList<Long> ids = new ArrayList<>(pictures.length);
        Arrays.stream(pictures).forEach(picture -> {
            try {
                String picturePath = uploadPicture(picture);
                ids.add(saveGoodsPicture(goodsDetailId, picturePath));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return ids;
    }

    /**
     * 根据商品详情id删除图片
     *
     * @param goodsDetailId 商品id
     */
    @Override
    public void batchRemoveByGoodsDetailId(Long goodsDetailId) {
        List<GoodsDetailPictureDO> pictures = goodsDetailPictureDAO.listByGoodsDetailId(goodsDetailId);
        pictures.forEach(picture -> {
            FileUtils.deleteFile(picture.getPicturePath());
        });
        goodsDetailPictureDAO.removeByGoodsDetailId(goodsDetailId);
    }

    /**
     * 新增商品图片
     *
     * @param goodsDetailId 商品id
     * @param picturePath   图片路径
     * @return 商品图片DO
     */
    private Long saveGoodsPicture(Long goodsDetailId, String picturePath) throws Exception {
        GoodsDetailPictureDO picture = new GoodsDetailPictureDO();
        picture.setGoodsDetailId(goodsDetailId);
        picture.setPicturePath(picturePath);
        picture.setGmtCreate(dateProvider.getCurrentTime());
        picture.setGmtModified(dateProvider.getCurrentTime());
        return goodsDetailPictureDAO.save(picture);
    }

    /**
     * 上传图片
     *
     * @param picture 图片
     * @return 图片存储路径
     * @throws Exception
     */
    private String uploadPicture(MultipartFile picture) throws Exception {
        String realUploadDirPath = getUploadDirPath();
        return FileUtils.uploadFile(picture, realUploadDirPath);
    }

    /**
     * 获取最终图片上传的路径
     *
     * @return 图片上传的路径
     * @throws Exception
     */
    private String getUploadDirPath() throws Exception {
        String realUploadDirPath = uploadPath;
        if (PathType.RELATIVE.equals(uploadPathType)) {
            realUploadDirPath = FileUtils.getPathByRelative(uploadPath);
        }
        return realUploadDirPath;
    }
}
