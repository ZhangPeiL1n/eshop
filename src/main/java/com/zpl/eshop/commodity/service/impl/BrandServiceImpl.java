package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.BrandDAO;
import com.zpl.eshop.commodity.domain.BrandDO;
import com.zpl.eshop.commodity.domain.BrandDTO;
import com.zpl.eshop.commodity.domain.BrandQuery;
import com.zpl.eshop.commodity.service.BrandService;
import com.zpl.eshop.common.constant.PathType;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.FileUtils;
import com.zpl.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 品牌管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 12:07
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements BrandService {

    /**
     * 品牌管理DAO组件
     */
    @Autowired
    private BrandDAO brandDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * logo图片的路径类型
     */
    @Value("${commodity.brand.image.upload.logo.path.type}")
    private String logoPathType;

    /**
     * logo图片的上传路径
     */
    @Value("${commodity.brand.image.upload.logo.path}")
    private String logoPath;

    /**
     * 品牌授权图片的路径类型
     */
    @Value("${commodity.brand.image.upload.auth-voucher.path.type}")
    private String authVoucherPathType;

    /**
     * 品牌授权图片的上传路径
     */
    @Value("${commodity.brand.image.upload.auth-voucher.path}")
    private String authVoucherPath;


    /**
     * 分页查询品牌
     *
     * @param query 查询条件
     * @return 品牌列表
     */
    @Override
    public List<BrandDTO> listByPage(BrandQuery query) throws Exception {
        return ObjectUtils.convertList(brandDAO.listByPage(query), BrandDTO.class);
    }

    /**
     * 根据id查品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    @Override
    public BrandDTO getById(Long id) throws Exception {
        return brandDAO.getById(id).clone(BrandDTO.class);
    }

    /**
     * 新增品牌
     *
     * @param brand           品牌
     * @param logoFile        logo图片
     * @param authVoucherFile 品牌授权认证图片
     */
    @Override
    public void save(BrandDTO brand, MultipartFile logoFile, MultipartFile authVoucherFile) throws Exception {
        String logoPath = uploadLogoFile(logoFile);
        String authVoucherPath = uploadAuthVoucherFile(authVoucherFile);
        brand.setLogoPath(logoPath);
        brand.setAuthVoucherPath(authVoucherPath);
        brand.setGmtCreate(dateProvider.getCurrentTime());
        brand.setGmtModified(dateProvider.getCurrentTime());
        brandDAO.save(brand.clone(BrandDO.class));
    }

    /**
     * 上传 logo 图片
     *
     * @param logoFile logo文件
     * @throws Exception
     */
    private String uploadLogoFile(MultipartFile logoFile) throws Exception {
        if (logoFile == null) {
            return null;
        }
        String uploadPath;
        if (PathType.RELATIVE.equals(logoPathType)) {
            uploadPath = FileUtils.getPathByRelative(logoPath);
        } else {
            uploadPath = logoPath;
        }
        return FileUtils.uploadFile(logoFile, uploadPath);
    }

    /**
     * 上传 logo 图片
     *
     * @param authVoucherFile 品牌授权文件
     * @throws Exception
     */
    private String uploadAuthVoucherFile(MultipartFile authVoucherFile) throws Exception {
        if (authVoucherFile == null) {
            return null;
        }
        String uploadPath;
        if (PathType.RELATIVE.equals(authVoucherPathType)) {
            uploadPath = FileUtils.getPathByRelative(authVoucherPath);
        } else {
            uploadPath = authVoucherPath;
        }
        return FileUtils.uploadFile(authVoucherFile, uploadPath);
    }

    /**
     * 更新批判
     *
     * @param brand 品牌
     */
    @Override
    public void update(BrandDTO brand) throws Exception {
        brand.setGmtModified(dateProvider.getCurrentTime());
        brandDAO.update(brand.clone(BrandDO.class));
    }

    /**
     * 更新品牌图片
     *
     * @param id       品牌id
     * @param logoFile 图片文件
     */
    @Override
    public void updateLogoPicture(Long id, MultipartFile logoFile) throws Exception {
        BrandDO brand = brandDAO.getById(id);
        FileUtils.deleteFile(brand.getLogoPath());
        String logoPath = uploadLogoFile(logoFile);
        brand.setLogoPath(logoPath);
        brand.setGmtModified(dateProvider.getCurrentTime());
        brandDAO.updateLogoPicture(brand);
    }

    /**
     * 更新品牌授权认证图片
     *
     * @param id              品牌id
     * @param authVoucherFile 授权认证图片
     */
    @Override
    public void updateAuthVoucherPicture(Long id, MultipartFile authVoucherFile) throws Exception {
        BrandDO brand = brandDAO.getById(id);
        String authVoucherPath = uploadAuthVoucherFile(authVoucherFile);
        FileUtils.deleteFile(brand.getAuthVoucherPath());
        brand.setAuthVoucherPath(authVoucherPath);
        brand.setGmtModified(dateProvider.getCurrentTime());
        brandDAO.updateAuthVoucherPicture(brand);
    }

    /**
     * 删除品牌
     *
     * @param id 品牌id
     */
    @Override
    public void remove(Long id) {
        BrandDO brand = brandDAO.getById(id);
        FileUtils.deleteFile(brand.getLogoPath());
        FileUtils.deleteFile(brand.getAuthVoucherPath());
        brandDAO.remove(id);
    }
}
