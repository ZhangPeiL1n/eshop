package com.zpl.eshop.membership.service.impl;


import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.dao.UserDetailDAO;
import com.zpl.eshop.membership.domain.UserDetailDO;
import com.zpl.eshop.membership.domain.UserDetailDTO;
import com.zpl.eshop.membership.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户详细信息管理service组件
 *
 * @author ZhangPeiL1n
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailServiceImpl implements UserDetailService {

    /**
     * 用户详细信息管理DAO组件
     */
    @Autowired
    private UserDetailDAO userDetailDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 根据用户账号id查询用户详细信息
     *
     * @param userAccountId 用户账号id
     * @return 用户详细信息
     */
    @Override
    public UserDetailDTO getByUserAccountId(Long userAccountId) throws Exception {
        return userDetailDAO.getByUserAccountId(userAccountId).clone(UserDetailDTO.class);
    }

    /**
     * 更新用户详细信息
     *
     * @param userDetail 用户详细信息
     */
    @Override
    public void updateByUserAccountId(UserDetailDTO userDetail) throws Exception {
        userDetail.setGmtModified(dateProvider.getCurrentTime());
        userDetailDAO.updateByUserAccountId(userDetail.clone(UserDetailDO.class));
    }

}
