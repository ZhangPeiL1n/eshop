package com.zpl.eshop.promotion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 优惠券查询条件
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponQuery {

	/**
	 * 分页查询起始位置
	 */
	private Integer offset;

    /**
	 * 每页显示的数据条数
	 */
	private Integer size;

    /**
	 * 优惠券名称
	 */
	private String name;

    /**
	 * 优惠群类型
	 */
	private Integer type;

    /**
	 * 有效期开始时间
	 */
	private String validStartTime;

    /**
	 * 有效期结束时间
	 */
	private String validEndTime;

    /**
	 * 发放类型
	 */
	private Integer giveOutType;

    /**
	 * 优惠券状态
	 */
	private Integer status;
}
