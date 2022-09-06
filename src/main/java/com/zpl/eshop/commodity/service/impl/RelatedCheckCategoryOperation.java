package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.CategoryDAO;
import com.zpl.eshop.commodity.dao.GoodsDAO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 类目是否被商品关联检查操作
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 22:42
 **/
@Getter
@Component
@Scope("prototype")
public class RelatedCheckCategoryOperation extends AbstractCategoryOperation<Boolean> {

    /**
     * 商品DAO 组件
     */
    private final GoodsDAO goodsDAO;
    /**
     * 是否被商品关联
     */
    private Boolean related = false;

    @Autowired
    public RelatedCheckCategoryOperation(CategoryDAO categoryDAO, GoodsDAO goodsDAO) {
        super(categoryDAO);
        this.goodsDAO = goodsDAO;
    }

    @Override
    protected void doRealExecute(Category category) throws Exception {
        Long count = goodsDAO.countByCategoryId(category.getCategoryId());
        if (count > 0) {
            related = true;
        }
    }

    /**
     * 获取操作结果
     *
     * @return 返回操作结果
     * @throws Exception
     */
    public Boolean getResult() throws Exception {
        return related;
    }
}
