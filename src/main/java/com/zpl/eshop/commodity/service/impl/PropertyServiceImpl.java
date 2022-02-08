package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.PropertyDAO;
import com.zpl.eshop.commodity.domain.PropertyDO;
import com.zpl.eshop.commodity.domain.PropertyDTO;
import com.zpl.eshop.commodity.domain.PropertyQuery;
import com.zpl.eshop.commodity.service.PropertyService;
import com.zpl.eshop.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/19 22:05
 **/
@Service
public class PropertyServiceImpl implements PropertyService {

    private final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);
    /**
     * 商品属性管理模块Mapper组件
     */
    @Autowired
    private PropertyDAO propertyDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询商品属性
     *
     * @param propertyQuery 查询条件
     * @return 商品属性
     */
    @Override
    public List<PropertyDTO> listPropertiesByPage(PropertyQuery propertyQuery) {
        try {
            List<PropertyDO> propertyDOList = propertyDAO.listPropertiesByPage(propertyQuery);
            List<PropertyDTO> propertyDTOList = new ArrayList<>(propertyDOList.size());
            for (PropertyDO propertyDO : propertyDOList) {
                propertyDTOList.add(propertyDO.clone(PropertyDTO.class));
            }
            return propertyDTOList;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 新增商品属性
     *
     * @param propertyDTO 商品属性DO
     */
    @Override
    public Boolean saveProperty(PropertyDTO propertyDTO) {
        try {
            propertyDTO.setGmtCreate(dateProvider.getCurrentTime());
            propertyDTO.setGmtModified(dateProvider.getCurrentTime());
            propertyDAO.saveProperty(propertyDTO.clone(PropertyDO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 根据id查询商品属性
     *
     * @param propertyId 属性id
     * @return 商品属性DO
     */
    @Override
    public PropertyDTO getPropertyById(Long propertyId) {
        try {
            return propertyDAO.getPropertyById(propertyId).clone(PropertyDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new PropertyDTO();
        }
    }

    /**
     * 更新商品属性
     *
     * @param propertyDTO 商品属性DO
     */
    @Override
    public Boolean updateProperty(PropertyDTO propertyDTO) {
        try {
            propertyDTO.setGmtModified(dateProvider.getCurrentTime());
            propertyDAO.updateProperty(propertyDTO.clone(PropertyDO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
