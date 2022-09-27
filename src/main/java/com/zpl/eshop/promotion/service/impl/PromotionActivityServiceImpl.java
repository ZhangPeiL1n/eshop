package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.constant.PromotionActivityStatus;
import com.zpl.eshop.promotion.dao.PromotionActivityDAO;
import com.zpl.eshop.promotion.dao.PromotionActivityGoodsRelationDAO;
import com.zpl.eshop.promotion.domain.*;
import com.zpl.eshop.promotion.service.PromotionActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 促销活动管理service组件
 * @author ZhangPeiL1n
 *
 */
@Service
@Transactional
public class PromotionActivityServiceImpl implements PromotionActivityService {
	
	/**
	 * 促销活动管理DAO组件
	 */
	@Autowired
	private PromotionActivityDAO promotionActivityDAO;
	/**
	 * 促销活动与商品关联关系管理DAO组件
	 */
	@Autowired
	private PromotionActivityGoodsRelationDAO relationDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 分页查询促销活动
	 * @param query 查询条件
	 * @return 促销活动
	 */
	public List<PromotionActivityDTO> listByPage(
			PromotionActivityQuery query) throws Exception {
		return ObjectUtils.convertList(promotionActivityDAO.listByPage(query), 
				PromotionActivityDTO.class);
	}
	
	/**
	 * 根据id查询促销活动
	 * @param id 促销活动id
	 * @return 促销活动
	 */
	public PromotionActivityDTO getById(Long id) throws Exception {
		PromotionActivityDTO activity = promotionActivityDAO.getById(id)
				.clone(PromotionActivityDTO.class); 
		activity.setRelations(ObjectUtils.convertList(relationDAO.listByActivityId(id), 
				PromotionActivityGoodsRelationDTO.class));  
		return activity;
	}
	
	/**
	 * 新增促销活动
	 * @param activity 促销活动
	 */
	public void save(PromotionActivityDTO activity) throws Exception {
		activity.setGmtCreate(dateProvider.getCurrentTime()); 
		activity.setGmtModified(dateProvider.getCurrentTime());  
		activity.setStatus(PromotionActivityStatus.DISABLED); 
		
		Long promotionActivityId = promotionActivityDAO.save(
				activity.clone(PromotionActivityDO.class));  
		activity.setId(promotionActivityId); 
		
		saveRelations(activity); 
	}
	
	/**
	 * 更新促销活动
	 * @param activity 促销活动
	 */
	public void update(PromotionActivityDTO activity) throws Exception {
		activity.setGmtModified(dateProvider.getCurrentTime());  
		promotionActivityDAO.update(activity.clone(PromotionActivityDO.class));  
		
		relationDAO.removeByActivityId(activity.getId()); 
		
		saveRelations(activity); 
	}
	
	/**
	 * 删除促销活动
	 * @param id 促销活动id
	 */
	public void remove(Long id) throws Exception {
		relationDAO.removeByActivityId(id); 
		promotionActivityDAO.remove(id); 
	}
	
	/**
	 * 新增促销活动与商品的关联关系
	 * @param activity
	 * @throws Exception
	 */
	private void saveRelations(PromotionActivityDTO activity) throws Exception {
		for(PromotionActivityGoodsRelationDTO relation : activity.getRelations()) {
			relation.setPromotionActivityId(activity.getId());  
			relation.setGmtCreate(dateProvider.getCurrentTime()); 
			relation.setGmtModified(dateProvider.getCurrentTime());  
			relationDAO.save(relation.clone(PromotionActivityGoodsRelationDO.class));  
		}
	}
	
}
