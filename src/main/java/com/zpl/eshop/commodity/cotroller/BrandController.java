package com.zpl.eshop.commodity.cotroller;

import com.zpl.eshop.commodity.domain.BrandDTO;
import com.zpl.eshop.commodity.domain.BrandQuery;
import com.zpl.eshop.commodity.domain.BrandVO;
import com.zpl.eshop.commodity.service.BrandService;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 品牌管理Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 13:07
 **/
@RestController("/commodity/brand")
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
    /**
     * 品牌管理Service组件
     */
    @Autowired
    private BrandService brandService;

    /**
     * 根据分页查询品牌列表
     *
     * @param query 品牌查询条件
     * @return 品牌列表
     */
    @GetMapping("/")
    List<BrandVO> listPropertiesByPage(BrandQuery query) {
        try {
            return ObjectUtils.convertList(brandService.listByPage(query), BrandVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    @GetMapping("/{id}")
    public BrandVO getById(@PathVariable("id") Long id) {
        try {
            return brandService.getById(id).clone(BrandVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new BrandVO();
        }
    }

    /**
     * 新增品牌
     *
     * @param brandVO 品牌VO
     */
    @PostMapping("/")
    public Boolean saveProperty(BrandVO brandVO, MultipartFile logoFile, MultipartFile authVoucherFile) {
        try {
            brandService.save(brandVO.clone(BrandDTO.class), logoFile, authVoucherFile);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新品牌
     *
     * @param brandVO 品牌属性VO
     */
    @PutMapping("/")
    public Boolean update(@RequestBody BrandVO brandVO) {
        try {
            brandService.update(brandVO.clone(BrandDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新商品属性
     *
     * @param id       品牌id
     * @param logoFile logo图片
     */
    @PostMapping("/logo/{id}")
    public Boolean updateLogoPicture(@PathVariable("id") Long id, MultipartFile logoFile) {
        try {
            brandService.updateLogoPicture(id, logoFile);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新品牌授权认证图片
     *
     * @param id       品牌id
     * @param logoFile 授权认证图片
     * @return
     */
    @PostMapping("/auth_voucher/{id}")
    public Boolean updateAuthVoucherPicture(@PathVariable("id") Long id, MultipartFile logoFile) {
        try {
            brandService.updateAuthVoucherPicture(id, logoFile);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 删除品牌
     *
     * @param id 品牌id
     * @return
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            brandService.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 显示logo图片
     *
     * @param id       图片
     * @param request  请求
     * @param response 响应
     */
    @GetMapping("/logo/{id}")
    public void viewLogo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        try {
            BrandDTO brand = brandService.getById(id);
            File file = new File(brand.getLogoPath());
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            response.setContentType("image/jpg");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            logger.error("error", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("error", e);
                }
            }
        }
    }

    /**
     * 显示品牌授权图片
     *
     * @param id       图片
     * @param request  请求
     * @param response 响应
     */
    @GetMapping("/authVoucher/{id}")
    public void viewAuthVoucher(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        try {
            BrandDTO brand = brandService.getById(id);
            File file = new File(brand.getAuthVoucherPath());
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            response.setContentType("image/jpg");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            logger.error("error", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("error", e);
                }
            }
        }
    }
}
