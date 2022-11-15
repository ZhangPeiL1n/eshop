package com.zpl.eshop.membership.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.domain.UserAccountDTO;
import com.zpl.eshop.membership.service.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/2 18:58
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class MembershipServiceImpl implements MembershipService {

    private static final Logger logger = LoggerFactory.getLogger(MembershipServiceImpl.class);

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 通知会员中心，“用户今日第一次登陆”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informFirstLoginDailyEvent(Long userAccountId) throws Exception {
        return true;
    }

    /**
     * 通知会员中心，“支付订单”事件发生了
     *
     * @param userAccountId    用户帐号 id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informPayOrderEvent(Long userAccountId, Double totalOrderAmount) throws Exception {
        return true;
    }

    /**
     * 通知会员中心，“完成退货”事件发生了
     *
     * @param userAccountId    用户帐号 id
     * @param totalOrderAmount 订单总金额
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informFinishReturnGoodsEvent(Long userAccountId, Double totalOrderAmount) throws Exception {
        return true;
    }

    /**
     * 通知会员中心，“发表评论”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @param showPictures  是否晒图
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informPublishCommentEvent(Long userAccountId, Boolean showPictures) throws Exception {
        return true;
    }

    /**
     * 通知会员中心，“删除评论”事件发生了
     *
     * @param userAccountId 用户帐号 id
     * @param showPictures  是否晒图
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informRemoveCommentEvent(Long userAccountId, Boolean showPictures) throws Exception {
        return true;
    }

    /**
     * 查询所有的用户账户
     *
     * @return 用户账户
     * @throws Exception
     */
    @Override
    public List<UserAccountDTO> listAllUserAccounts() throws Exception {
        List<UserAccountDTO> userAccounts = new ArrayList<>();

        UserAccountDTO userAccount = new UserAccountDTO();
        userAccount.setUsername("zhangsan");
        userAccount.setPassword("12345678");
        userAccount.setEmail("zhangsan@sian.com");
        userAccount.setCellPhoneNumber("18967543209");
        userAccounts.add(userAccount);

        return userAccounts;
    }
}
