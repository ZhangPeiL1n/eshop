package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.BrandDO;
import com.zpl.eshop.commodity.domain.BrandQuery;

import java.util.List;

/**
 * 品牌管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/8 22:16
 **/
public interface BrandDAO {

    /**
     * 分页查询品牌
     *
     * @param query 分页查询条件
     * @return 品牌列表
     */
    List<BrandDO> listByPage(BrandQuery query);


    /**
     * 根据id查品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    BrandDO getById(Long id);

    /**
     * 新增品牌
     *
     * @param brand 品牌
     */
    void save(BrandDO brand);

    /**
     * 更新品牌
     *
     * @param brand 品牌
     */
    void update(BrandDO brand);

    /**
     * 更新品牌logo图片路径
     *
     * @param brand 品牌
     */
    void updateLogoPicture(BrandDO brand);

    /**
     * 更新品牌授权认证图片路径
     *
     * @param brand 品牌
     */
    void updateAuthVoucherPicture(BrandDO brand);

    /**
     * 删除品牌
     *
     * @param id 品牌id
     */
    void remove(Long id);
}
