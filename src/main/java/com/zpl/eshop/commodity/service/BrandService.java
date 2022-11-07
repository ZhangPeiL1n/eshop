package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.BrandDTO;
import com.zpl.eshop.commodity.domain.BrandQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 品牌管理模块Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/8 22:26
 **/
public interface BrandService {

    /**
     * 分页查询品牌
     *
     * @param query 查询条件
     * @return 品牌列表
     * @throws Exception
     */
    List<BrandDTO> listByPage(BrandQuery query) throws Exception;

    /**
     * 根据id查品牌
     *
     * @param id 品牌id
     * @return 品牌
     * @throws Exception
     */
    BrandDTO getById(Long id) throws Exception;

    /**
     * 新增品牌
     *
     * @param brand           品牌
     * @param logoFile        logo图片
     * @param authVoucherFile 品牌授权认证图片
     * @throws Exception
     */
    void save(BrandDTO brand, MultipartFile logoFile, MultipartFile authVoucherFile) throws Exception;

    /**
     * 更新品牌
     *
     * @param brand 品牌
     * @throws Exception
     */
    void update(BrandDTO brand) throws Exception;

    /**
     * 更新品牌图片
     *
     * @param id       品牌id
     * @param logoFile 图片文件
     * @throws Exception
     */
    void updateLogoPicture(Long id, MultipartFile logoFile) throws Exception;

    /**
     * 更新品牌授权认证图片
     *
     * @param id              品牌id
     * @param authVoucherFile 授权认证图片
     * @throws Exception
     */
    void updateAuthVoucherPicture(Long id, MultipartFile authVoucherFile) throws Exception;

    /**
     * 删除品牌
     *
     * @param id 品牌id
     * @throws Exception
     */
    void remove(Long id) throws Exception;

}
