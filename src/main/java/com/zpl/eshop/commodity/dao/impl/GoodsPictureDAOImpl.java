package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsPictureDAO;
import com.zpl.eshop.commodity.domain.GoodsPictureDO;
import com.zpl.eshop.commodity.mapper.GoodsPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品图片管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 22:03
 **/
@Repository
public class GoodsPictureDAOImpl implements GoodsPictureDAO {

    /**
     * 商品图片管理Mapper组件
     */
    @Autowired
    private GoodsPictureMapper goodsPictureMapper;

    /**
     * 新增图片
     *
     * @param picture 图片
     */
    @Override
    public void save(GoodsPictureDO picture) {
        goodsPictureMapper.save(picture);
    }
}
