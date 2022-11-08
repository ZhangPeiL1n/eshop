package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.PropertyDAO;
import com.zpl.eshop.commodity.domain.PropertyDO;
import com.zpl.eshop.commodity.domain.PropertyDTO;
import com.zpl.eshop.commodity.domain.PropertyQuery;
import com.zpl.eshop.commodity.service.PropertyService;
import com.zpl.eshop.common.bean.SpringApplicationContext;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/19 22:05
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PropertyServiceImpl implements PropertyService {

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
     * spring上下文
     */
    @Autowired
    private SpringApplicationContext context;

    /**
     * 分页查询商品属性
     *
     * @param propertyQuery 查询条件
     * @return 商品属性
     * @throws Exception
     */
    @Override
    public List<PropertyDTO> listPropertiesByPage(PropertyQuery propertyQuery) throws Exception {
        List<PropertyDO> propertyDOList = propertyDAO.listPropertiesByPage(propertyQuery);
        List<PropertyDTO> propertyDTOList = new ArrayList<>(propertyDOList.size());
        for (PropertyDO propertyDO : propertyDOList) {
            propertyDTOList.add(propertyDO.clone(PropertyDTO.class));
        }
        return propertyDTOList;
    }

    /**
     * 新增商品属性
     *
     * @param propertyDTO 商品属性DO
     * @throws Exception
     */
    @Override
    public Boolean saveProperty(PropertyDTO propertyDTO) throws Exception {
        propertyDTO.setGmtCreate(dateProvider.getCurrentTime());
        propertyDTO.setGmtModified(dateProvider.getCurrentTime());
        propertyDAO.saveProperty(propertyDTO.clone(PropertyDO.class));
        return true;
    }

    /**
     * 根据id查询商品属性
     *
     * @param propertyId 属性id
     * @return 商品属性DO
     * @throws Exception
     */
    @Override
    public PropertyDTO getPropertyById(Long propertyId) throws Exception {
        return propertyDAO.getPropertyById(propertyId).clone(PropertyDTO.class);
    }

    /**
     * 查询类目Id对应的所有属性
     *
     * @param categoryId 类目id
     * @return 属性
     * @throws Exception
     */
    @Override
    public Properties getPropertiesByCategoryId(Long categoryId) throws Exception {
        CategoryOperation<Properties> queryOptions = context.getBean(QueryPropertyCategoryOperation.class);
        return new Category(categoryId).execute(queryOptions);
    }

    /**
     * 更新商品属性
     *
     * @param propertyDTO 商品属性DO
     * @throws Exception
     */
    @Override
    public Boolean updateProperty(PropertyDTO propertyDTO) throws Exception {
        propertyDTO.setGmtModified(dateProvider.getCurrentTime());
        propertyDAO.updateProperty(propertyDTO.clone(PropertyDO.class));
        return true;
    }
}
