package com.zpl.eshop.membership.service.impl;

import com.zpl.eshop.membership.service.MembershipFacadeService;

/**
 * 会员中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/2 18:58
 **/
public class MembershipFacadeServiceImpl implements MembershipFacadeService {
    /**
     * 通知会员中心，“用户今日第一次登陆”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @return 处理结果
     */
    @Override
    public Boolean informFirstLoginDailyEvent(Long userAccountId) {
        return true;
    }

    /**
     * 通知会员中心，“支付订单”事件发生了
     *
     * @param userAccountId    用户帐号 id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(Long userAccountId, Long totalOrderAmount) {
        return true;
    }

    /**
     * 通知会员中心，“完成退货”事件发生了
     *
     * @param userAccountId    用户帐号 id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     */
    @Override
    public Boolean informFinishReturnGoodsEvent(Long userAccountId, Long totalOrderAmount) {
        return true;
    }

    /**
     * 通知会员中心，“发表评论”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @param showPictures  是否晒图
     * @return 处理结果
     */
    @Override
    public Boolean informPublishCommentEvent(Long userAccountId, Boolean showPictures) {
        return true;
    }

    /**
     * 通知会员中心，“删除评论”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @param showPictures  是否晒图
     * @return 处理结果
     */
    @Override
    public Boolean informRemoveCommentEvent(Long userAccountId, Boolean showPictures) {
        return true;
    }
}
