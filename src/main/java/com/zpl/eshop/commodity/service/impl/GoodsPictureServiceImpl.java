package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.GoodsPictureDAO;
import com.zpl.eshop.commodity.domain.GoodsPictureDO;
import com.zpl.eshop.commodity.domain.GoodsPictureDTO;
import com.zpl.eshop.commodity.service.GoodsPictureService;
import com.zpl.eshop.common.constant.PathType;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品图片管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 12:14
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsPictureServiceImpl implements GoodsPictureService {

    /**
     * 图片上传路径类型
     */
    @Value("${commodity.goods.image.upload.picture.path.type}")
    private String uploadPathType;

    /**
     * 上传路径
     */
    @Value("${commodity.goods.image.upload.picture.path}")
    private String uploadPath;

    /**
     * 商品图片管理DAO组件
     */
    @Autowired
    private GoodsPictureDAO goodsPictureDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 根据商品id查询商品图片id
     *
     * @param goodsId 商品id
     * @return 商品图片id
     * @throws Exception
     */
    @Override
    public List<Long> listIdsByGoodsId(Long goodsId) throws Exception {
        return goodsPictureDAO.listIdsByGoodsId(goodsId);
    }

    /**
     * 根据id查询商品图片
     *
     * @param id 商品图片id
     * @return 商品图片
     * @throws Exception
     */
    @Override
    public GoodsPictureDTO getById(Long id) throws Exception {
        return goodsPictureDAO.getById(id).clone(GoodsPictureDTO.class);
    }

    /**
     * 批量上传图片
     *
     * @param goodsId  商品id
     * @param pictures 商品图片
     * @throws Exception
     */
    @Override
    public void batchSave(Long goodsId, MultipartFile[] pictures) throws Exception {
        for (MultipartFile picture : pictures) {
            String picturePath = uploadPicture(picture);
            saveGoodsPicture(goodsId, picturePath);
        }
    }

    /**
     * 根据商品id删除图片
     *
     * @param goodsId 商品id
     * @throws Exception
     */
    @Override
    public void batchRemoveByGoodsId(Long goodsId) throws Exception {
        List<GoodsPictureDO> pictures = goodsPictureDAO.listByGoodsId(goodsId);
        pictures.forEach(picture -> {
            FileUtils.deleteFile(picture.getPicturePath());
        });
        goodsPictureDAO.removeByGoodsId(goodsId);
    }

    /**
     * 新增商品图片
     *
     * @param goodsId     商品id
     * @param picturePath 图片路径
     * @return 商品图片DO
     * @throws Exception
     */
    private void saveGoodsPicture(Long goodsId, String picturePath) throws Exception {
        GoodsPictureDO picture = new GoodsPictureDO();
        picture.setGoodsId(goodsId);
        picture.setPicturePath(picturePath);
        picture.setGmtCreate(dateProvider.getCurrentTime());
        picture.setGmtModified(dateProvider.getCurrentTime());
        goodsPictureDAO.save(picture);
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
     * @return 上传路径
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
