package com.zpl.eshop.membership.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.dao.UserDetailDAO;
import com.zpl.eshop.membership.domain.UserDetailDO;
import com.zpl.eshop.membership.mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户详细信息管理DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class UserDetailDAOImpl implements UserDetailDAO {

    /**
     * 用户详细信息管理mapper组件
     */
    @Autowired
    private UserDetailMapper userDetailMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增用户详细信息
     *
     * @param userDetail 用户详细信息
     * @throws Exception
     */
    @Override
    public void save(UserDetailDO userDetail) throws Exception {
        userDetail.setGmtCreate(dateProvider.getCurrentTime());
        userDetail.setGmtModified(dateProvider.getCurrentTime());
        userDetailMapper.save(userDetail);
    }

    /**
     * 根据用户账号id查询用户详细信息
     *
     * @param userAccountId 用户账号id
     * @return 用户详细信息
     */
    @Override
    public UserDetailDO getByUserAccountId(Long userAccountId) {
        return userDetailMapper.getByUserAccountId(userAccountId);
    }

    /**
     * 更新用户详细信息
     *
     * @param userDetail 用户详细信息
     * @throws Exception
     */
    @Override
    public void updateByUserAccountId(UserDetailDO userDetail) throws Exception {
        userDetail.setGmtModified(dateProvider.getCurrentTime());
        userDetailMapper.updateByUserAccountId(userDetail);
    }

}
