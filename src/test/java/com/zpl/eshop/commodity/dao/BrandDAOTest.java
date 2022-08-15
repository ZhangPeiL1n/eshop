package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.BrandDO;
import com.zpl.eshop.commodity.domain.BrandQuery;
import com.zpl.eshop.common.util.DateProvider;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌管理模块DAO组件单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/8/15 20:57
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class BrandDAOTest {

    /**
     * 品牌管理模块DAO组件
     */
    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增品牌
     */
    @Test
    public void testSaveBrand() throws Exception {
        BrandDO brandDO = createBrandDO();
        Long brandId = brandDO.getId();
        Assert.assertNotNull(brandId);
        Assert.assertThat(brandId, Matchers.greaterThan(0L));
    }

    /**
     * 测试分页查询品牌
     *
     * @throws Exception
     */
    @Test
    public void testListByPage() throws Exception {
        Map<Long, BrandDO> brandMap = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            BrandDO comment = createBrandDO();
            brandMap.put(comment.getId(), comment);
        }
        Integer offset = 10;
        Integer size = 5;
        BrandQuery query = new BrandQuery();
        query.setOffset(offset);
        query.setSize(size);
        query.setChineseName("测试");
        query.setEnglishName("test");
        query.setAliasName("测试");

        List<BrandDO> resultBrands = brandDAO.listByPage(query);

        Assert.assertEquals((int) size, resultBrands.size());
        resultBrands.forEach(resultBrand -> Assert.assertEquals(brandMap.get(resultBrand.getId()), resultBrand));
    }

    /**
     * 测试根据id获取品牌
     *
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception {
        BrandDO brandDO = createBrandDO();
        BrandDO resultBrand = brandDAO.getById(brandDO.getId());
        Assert.assertEquals(brandDO, resultBrand);
    }

    /**
     * 测试更新品牌
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        BrandDO brandDO = createBrandDO();
        brandDO.setChineseName("修改后的品牌名称");
        brandDO.setGmtModified(dateProvider.getCurrentTime());
        brandDAO.update(brandDO);

        BrandDO resultBrand = brandDAO.getById(brandDO.getId());
        Assert.assertEquals(brandDO, resultBrand);
    }

    /**
     * 测试删除品牌
     *
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        BrandDO brandDO = createBrandDO();
        brandDAO.remove(brandDO.getId());
        BrandDO resultBrand = brandDAO.getById(brandDO.getId());
        Assert.assertNull(resultBrand);
    }

    /**
     * 创建品牌DO
     *
     * @return DO
     * @throws Exception
     */
    private BrandDO createBrandDO() throws Exception {
        BrandDO brand = new BrandDO();

        brand.setChineseName("测试品牌");
        brand.setEnglishName("test brand");
        brand.setAliasName("测试品牌");
        brand.setLogoPath("test path");
        brand.setIntroduction("测试品牌");
        brand.setAuthVoucherPath("test path");
        brand.setLocation("测试地址");
        brand.setRemark("测试品牌");

        brand.setGmtCreate(dateProvider.getCurrentTime());
        brand.setGmtModified(dateProvider.getCurrentTime());
        brandDAO.save(brand);
        return brand;
    }
}
