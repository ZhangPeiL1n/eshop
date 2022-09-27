package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.promotion.domain.CouponAchieveDO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优惠券领取记录管理DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/9/27 8:57
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CouponAchieveDAOTest {

    /**
     * 优惠券领取记录管理DAO组件
     */
    @Autowired
    private CouponAchieveDAO couponAchieveDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试根据优惠券id和用户账号id查询优惠券的领取记录
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_coupon_achieve.sql"})
    public void testGetByUserAccountId() throws Exception {
        Long couponId = 1L;
        Long userAccountId = 1L;
        CouponAchieveDO expectedCouponAchieve = createCouponAchieve(couponId, userAccountId);

        CouponAchieveDO actualCouponAchieve = couponAchieveDAO.getByUserAccountId(couponId, userAccountId);

        Assert.assertEquals(expectedCouponAchieve, actualCouponAchieve);
    }

    /**
     * 测试新增优惠券领取记录
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_coupon_achieve.sql"})
    public void testSave() throws Exception {
        Long couponId = 1L;
        Long userAccountId = 1L;
        CouponAchieveDO expectedCouponAchieve = createCouponAchieve(couponId, userAccountId);
        Assert.assertNotNull(expectedCouponAchieve.getId());
        Assert.assertThat(expectedCouponAchieve.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试查询用户还没有使用过的优惠券领取记录
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_coupon_achieve.sql"})
    public void testListUnusedByUserAccountId() throws Exception {
        Long userAccountId = 1L;
        Integer count = 3;

        Map<Long, CouponAchieveDO> expectedCouponAchieves = createCouponAchieveMap(count, userAccountId);

        List<CouponAchieveDO> actualCouponAchieves =
                couponAchieveDAO.listUnusedByUserAccountId(userAccountId);

        compareCouponAchieves(count, expectedCouponAchieves, actualCouponAchieves);
    }

    /**
     * 测试更新优惠券领取记录
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_coupon_achieve.sql"})
    public void testUpdate() throws Exception {
        Long couponId = 1L;
        Long userAccountId = 1L;
        CouponAchieveDO expectedCouponAchieve = createCouponAchieve(couponId, userAccountId);

        expectedCouponAchieve.setUsed(1);
        couponAchieveDAO.update(expectedCouponAchieve);

        CouponAchieveDO actualCouponAchieve = couponAchieveDAO.getByUserAccountId(couponId, userAccountId);

        Assert.assertEquals(expectedCouponAchieve, actualCouponAchieve);
    }

    /**
     * 比较优惠券领取记录集合
     *
     * @param expectedSize             期望的促销集合大小
     * @param expectedCouponAchieveMap 期望的优惠券领取记录集合
     * @param actualCouponAchieves     实际的优惠券领取记录集合
     * @throws Exception
     */
    private void compareCouponAchieves(
            Integer expectedSize,
            Map<Long, CouponAchieveDO> expectedCouponAchieveMap,
            List<CouponAchieveDO> actualCouponAchieves) throws Exception {
        Assert.assertEquals((int) expectedSize, actualCouponAchieves.size());

        for (CouponAchieveDO actualCouponAchieve : actualCouponAchieves) {
            CouponAchieveDO expectedCouponAchieve = expectedCouponAchieveMap.get(actualCouponAchieve.getId());
            Assert.assertEquals(expectedCouponAchieve, actualCouponAchieve);
        }
    }

    /**
     * 创建优惠券领取记录map
     *
     * @param count 优惠券领取记录数量
     * @return
     * @throws Exception
     */
    private Map<Long, CouponAchieveDO> createCouponAchieveMap(
            Integer count, Long userAccountId) throws Exception {
        Map<Long, CouponAchieveDO> couponAchieveMap = new HashMap<>();

        List<CouponAchieveDO> couponAchieves = createCouponAchieves(count, userAccountId);
        for (CouponAchieveDO couponAchieve : couponAchieves) {
            couponAchieveMap.put(couponAchieve.getId(), couponAchieve);
        }

        return couponAchieveMap;
    }

    /**
     * 创建一个优惠券领取记录集合
     *
     * @return
     * @throws Exception
     */
    private List<CouponAchieveDO> createCouponAchieves(
            Integer count, Long userAccountId) throws Exception {
        List<CouponAchieveDO> couponAchieves = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            CouponAchieveDO couponAchieve = createCouponAchieve((long) i, userAccountId);
            couponAchieves.add(couponAchieve);
        }
        return couponAchieves;
    }

    /**
     * 创建优惠券领取记录
     *
     * @return 优惠券领取记录
     * @throws Exception
     */
    private CouponAchieveDO createCouponAchieve(
            Long couponId, Long userAccountId) throws Exception {
        CouponAchieveDO couponAchieve = new CouponAchieveDO();
        couponAchieve.setCouponId(couponId);
        couponAchieve.setUserAccountId(userAccountId);
        couponAchieve.setUsed(0);
        couponAchieve.setGmtCreate(dateProvider.getCurrentTime());
        couponAchieve.setGmtModified(dateProvider.getCurrentTime());

        couponAchieveDAO.save(couponAchieve);

        return couponAchieve;
    }
}
