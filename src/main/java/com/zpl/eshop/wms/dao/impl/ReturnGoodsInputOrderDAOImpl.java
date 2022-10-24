package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.ReturnGoodsInputOrderDAO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderQuery;
import com.zpl.eshop.wms.mapper.ReturnGoodsInputOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退户入库单管理DAO
 *
 * @author ZhangPeiL1n
 * @date 2022/10/24 22:32
 **/
@Repository
public class ReturnGoodsInputOrderDAOImpl implements ReturnGoodsInputOrderDAO {
    /**
     * 退货入库单管理mapper组件
     */
    @Autowired
    private ReturnGoodsInputOrderMapper returnGoodsInputOrderMapper;

    /**
     * 新增退货入库单
     *
     * @param returnGoodsInputOrder 退货入库单
     */
    @Override
    public Long save(ReturnGoodsInputOrderDO returnGoodsInputOrder) throws Exception {
        returnGoodsInputOrderMapper.save(returnGoodsInputOrder);
        return returnGoodsInputOrder.getId();
    }

    /**
     * 分页查询退货入库单
     *
     * @param query 查询条件
     * @return 退货入库单
     */
    @Override
    public List<ReturnGoodsInputOrderDO> listByPage(
            ReturnGoodsInputOrderQuery query) throws Exception {
        return returnGoodsInputOrderMapper.listByPage(query);
    }

    /**
     * 根据id查询退后入库单
     *
     * @param id 退货入库单id
     * @return 退后入库单
     */
    @Override
    public ReturnGoodsInputOrderDO getById(Long id) throws Exception {
        return returnGoodsInputOrderMapper.getById(id);
    }
}
