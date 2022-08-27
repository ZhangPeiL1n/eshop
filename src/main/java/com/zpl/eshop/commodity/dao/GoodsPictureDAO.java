package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsPictureDO;

/**
 * 商品图片管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 22:02
 **/
public interface GoodsPictureDAO {

    /**
     * 新增图片
     *
     * @param picture 图片
     */
    void save(GoodsPictureDO picture);
}
