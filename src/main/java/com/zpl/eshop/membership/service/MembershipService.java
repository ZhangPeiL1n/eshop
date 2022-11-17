package com.zpl.eshop.membership.service;

import com.zpl.eshop.membership.domain.UserAccountDTO;

import java.util.List;

/**
 * 会员中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 18:35
 **/
public interface MembershipService {

    /**
     * 通知会员中心，“用户今日第一次登陆”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informFirstLoginDailyEvent(Long userAccountId) throws Exception;

    /**
     * 通知会员中心，“支付订单”事件发生了
     *
     * @param userAccountId    用户帐号 id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPayOrderEvent(Long userAccountId, Double totalOrderAmount) throws Exception;

    /**
     * 通知会员中心，“完成退货”事件发生了
     *
     * @param userAccountId    用户帐号 id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     * @throws Exception
     */
    Boolean informFinishReturnGoodsEvent(Long userAccountId, Double totalOrderAmount) throws Exception;

    /**
     * 通知会员中心，“发表评论”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @param showPictures  是否晒图
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPublishCommentEvent(Long userAccountId, Boolean showPictures) throws Exception;

    /**
     * 通知会员中心，“删除评论”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @param showPictures  是否晒图
     * @return 处理结果
     * @throws Exception
     */
    Boolean informRemoveCommentEvent(Long userAccountId, Boolean showPictures) throws Exception;

    /**
     * 查询所有的用户账户
     *
     * @return 用户账户
     * @throws Exception
     */
    List<UserAccountDTO> listAllUserAccounts() throws Exception;
}
