package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.BrandDAO;
import com.zpl.eshop.commodity.domain.BrandDO;
import com.zpl.eshop.commodity.domain.BrandQuery;
import com.zpl.eshop.commodity.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 品牌管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/8 22:18
 **/
@Repository
public class BrandDAOImpl implements BrandDAO {

    /**
     * 品牌管理模块mapper组件
     */
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询品牌
     *
     * @param query 分页查询条件
     * @return 品牌列表
     */
    @Override
    public List<BrandDO> listByPage(BrandQuery query) {
        return brandMapper.listByPage(query);
    }


    /**
     * 根据id查品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    @Override
    public BrandDO getById(Long id) {
        return brandMapper.getById(id);
    }

    /**
     * 新增品牌
     *
     * @param brand 品牌
     */
    public void save(BrandDO brand) {
        brandMapper.save(brand);
    }

    /**
     * 更新品牌
     *
     * @param brand 品牌
     */
    @Override
    public void update(BrandDO brand) {
        brandMapper.update(brand);
    }

    /**
     * 删除品牌
     *
     * @param id 品牌id
     */
    @Override
    public void remove(Long id) {
        brandMapper.remove(id);
    }
}
