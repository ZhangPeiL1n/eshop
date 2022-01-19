package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.PropertyDTO;
import com.zpl.eshop.commodity.domain.PropertyQuery;
import com.zpl.eshop.commodity.domain.PropertyVO;
import com.zpl.eshop.commodity.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性管理模块Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/19 22:16
 **/
@RestController
@RequestMapping("/commodity/propert")
public class PropertyController {

    /**
     * 商品属性管理模块Service组件
     */
    @Autowired
    private PropertyService propertyService;

    private final Logger logger = LoggerFactory.getLogger(PropertyController.class);

    /**
     * 根据分页查询商品属性
     *
     * @param propertyQuery 商品属性查询条件
     * @return 商品属性
     */
    @GetMapping("/")
    List<PropertyVO> listPropertiesByPage(PropertyQuery propertyQuery) {
        try {
            List<PropertyDTO> propertyDTOList = propertyService.listPropertiesByPage(propertyQuery);
            ArrayList<PropertyVO> propertyVOList = new ArrayList<>(propertyDTOList.size());
            for (PropertyDTO propertyDTO : propertyDTOList) {
                propertyVOList.add(propertyDTO.clone(PropertyVO.class));
            }
            return propertyVOList;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 新增商品属性
     *
     * @param propertyVO 商品属性VO
     */
    @PostMapping("/")
    public Boolean saveProperty(@RequestBody PropertyVO propertyVO) {
        try {
            propertyService.saveProperty(propertyVO.clone(PropertyDTO.class));
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
     * @return 商品属性VO
     */
    @GetMapping("/{propertyId}")
    public PropertyVO getPropertyById(@PathVariable("propertyId") Long propertyId) {
        try {
            return propertyService.getPropertyById(propertyId).clone(PropertyVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new PropertyVO();
        }
    }

    /**
     * 更新商品属性
     *
     * @param propertyVO 商品属性VO
     */
    @PutMapping("/")
    public Boolean updateProperty(@RequestBody PropertyVO propertyVO) {
        try {
            propertyService.updateProperty(propertyVO.clone(PropertyDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
