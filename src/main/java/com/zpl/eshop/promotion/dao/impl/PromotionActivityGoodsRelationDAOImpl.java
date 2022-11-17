package com.zpl.eshop.promotion.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.promotion.dao.PromotionActivityGoodsRelationDAO;
import com.zpl.eshop.promotion.domain.PromotionActivityGoodsRelationDO;
import com.zpl.eshop.promotion.mapper.PromotionActivityGoodsRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 促销活动与商品关联关系管理的DAO组件
 * @author ZhangPeiL1n
 *
 */
@Repository
public class PromotionActivityGoodsRelationDAOImpl implements PromotionActivityGoodsRelationDAO {

	/**
	 * 促销活动与商品关联关系管理的mapper组件
	 */
	@Autowired
	private PromotionActivityGoodsRelationMapper relationMapper;

	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;

	/**
	 * 根据促销活动id查询促销活动与商品的关联关系
	 *
	 * @param promotionActivityId 促销活动id
	 * @return 促销活动与商品的关联关系
	 * @throws Exception
	 */
	public List<PromotionActivityGoodsRelationDO> listByActivityId(
			Long promotionActivityId) throws Exception {
		return relationMapper.listByActivityId(promotionActivityId);
	}

	/**
	 * 新增促销活动与商品的关联关系
	 *
	 * @param relation 促销活动与商品的关联关系
	 * @throws Exception
	 */
	public void save(PromotionActivityGoodsRelationDO relation) throws Exception {
		relation.setGmtCreate(dateProvider.getCurrentTime());
		relation.setGmtModified(dateProvider.getCurrentTime());
		relationMapper.save(relation);
	}

	/**
	 * 删除促销活动对应的与商品的关联关系
	 *
	 * @param promotionActivityId 促销活动id
	 * @throws Exception
	 */
	public void removeByActivityId(Long promotionActivityId) throws Exception {
		relationMapper.removeByActivityId(promotionActivityId); 
	}
}
