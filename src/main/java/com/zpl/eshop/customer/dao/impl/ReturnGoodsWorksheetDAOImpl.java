package com.zpl.eshop.customer.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.customer.dao.ReturnGoodsWorksheetDAO;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDO;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetQuery;
import com.zpl.eshop.customer.mapper.ReturnGoodsWorksheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服中心退货工单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 21:56
 **/
@Repository
public class ReturnGoodsWorksheetDAOImpl implements ReturnGoodsWorksheetDAO {

    /**
     * 客服中心退货工单管理Mapper组件
     */
    @Autowired
    private ReturnGoodsWorksheetMapper returnGoodsWorksheetMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增退货工单
     *
     * @param worksheet 退货工单
     */
    @Override
    public void save(ReturnGoodsWorksheetDO worksheet) throws Exception {
        worksheet.setGmtCreate(dateProvider.getCurrentTime());
        worksheet.setGmtModified(dateProvider.getCurrentTime());
        returnGoodsWorksheetMapper.save(worksheet);
    }

    /**
     * 分页查询退货工单
     *
     * @param query 查询条件
     * @return 退货工单
     */
    @Override
    public List<ReturnGoodsWorksheetDO> listByPage(ReturnGoodsWorksheetQuery query) {
        return returnGoodsWorksheetMapper.listByPage(query);
    }

    /**
     * 根据id查询退货工单
     *
     * @param id 退货工单id
     * @return 退货工单
     */
    @Override
    public ReturnGoodsWorksheetDO getById(Long id) {
        return returnGoodsWorksheetMapper.getById(id);
    }

    /**
     * 根据订单id查询退货工单
     *
     * @param orderInfoId 订单id
     * @return 退货工单
     */
    @Override
    public ReturnGoodsWorksheetDO getByOrderInfoId(Long orderInfoId) {
        return returnGoodsWorksheetMapper.getByOrderInfoId(orderInfoId);
    }

    /**
     * 更新退货工单的状态
     *
     * @param worksheet 退货工单
     */
    @Override
    public void updateStatus(ReturnGoodsWorksheetDO worksheet) {
        returnGoodsWorksheetMapper.updateStatus(worksheet);
    }

    /**
     * 更新退货工单的退货物流单号
     *
     * @param worksheet 退货工单
     */
    @Override
    public void updateReturnGoodsLogisticsCode(ReturnGoodsWorksheetDO worksheet) {
        returnGoodsWorksheetMapper.updateReturnGoodsLogisticsCode(worksheet);
    }

}
