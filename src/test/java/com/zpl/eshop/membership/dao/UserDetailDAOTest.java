package com.zpl.eshop.membership.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.domain.UserDetailDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * 用户详细信息管理DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
@Rollback()
public class UserDetailDAOTest {


	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;

	/**
	 * 用户详细信息管理DAO组件
	 */
	@Autowired
	private UserDetailDAO userDetailDAO;


	/**
	 * 测试查询用户账号的所有用户详细信息
	 *
	 * @throws Exception
	 */
	@Test
	@Sql({"clean_user_detail.sql"})
	public void testGetByUserAccountId() throws Exception {
		Long userAccountId = 1L;
		UserDetailDO expectedUserDetail = createUserDetail(userAccountId);
		UserDetailDO actualUserDetail = userDetailDAO.getByUserAccountId(userAccountId);
		assertEquals(expectedUserDetail, actualUserDetail);
	}


	/**
	 * 测试新增用户详细信息
	 *
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {
		Long userAccountId = 1L;
		UserDetailDO expectedUserDetail = createUserDetail(userAccountId);
		assertNotNull(expectedUserDetail.getId());
		assertThat(expectedUserDetail.getId(), greaterThan(0L));
	}


	/**
	 * 测试更新用户详细信息
	 *
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		Long userAccountId = 1L;
		UserDetailDO expectedUserDetail = createUserDetail(userAccountId);

		expectedUserDetail.setAddress("修改后的" + expectedUserDetail.getAddress());
		userDetailDAO.updateByUserAccountId(expectedUserDetail);

		UserDetailDO actualUserDetail = userDetailDAO.getByUserAccountId(userAccountId);

		assertEquals(expectedUserDetail, actualUserDetail);
	}


	/**
	 * 创建用户详细信息
	 *
	 * @param userAccountId 用户账号id
	 * @return 用户详细信息
	 * @throws Exception
	 */
	private UserDetailDO createUserDetail(
			Long userAccountId) throws Exception {
		UserDetailDO userDetail = new UserDetailDO();
		userDetail.setUserAccountId(userAccountId);
		userDetail.setAddress("测试住址");
		userDetail.setBirthday(dateProvider.getCurrentTime());
		userDetail.setGender(1);
		userDetail.setIdNumber("测试身份证号");
		userDetail.setName("测试姓名");
		userDetail.setGmtCreate(dateProvider.getCurrentTime());
		userDetail.setGmtModified(dateProvider.getCurrentTime());

		userDetailDAO.save(userDetail);
		userDetailDAO.updateByUserAccountId(userDetail);

		return userDetail;
	}

}
