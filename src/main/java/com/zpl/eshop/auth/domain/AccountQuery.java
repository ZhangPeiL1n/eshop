package com.zpl.eshop.auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账号查询条件
 *
 * @author ZhangPeiL1n
 */
@Data
public class AccountQuery {

	/**
	 * 分页查询起始位置
	 */
	private Integer offset;

	/**
	 * 每页查询记录数
	 */
	private Integer size;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 员工姓名
	 */
	private String name;
}
