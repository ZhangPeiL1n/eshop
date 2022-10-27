package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsDetailDO;
import com.zpl.eshop.common.util.DateProvider;
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

/**
 * 商品详情单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/9/11 2:05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class GoodsDetailDAOTest {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * 商品详情管理DAO组件
     */
    @Autowired
    private GoodsDetailDAO goodsDetailDAO;

    /**
     * 测试根据id查询商品详情
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_goods_detail.sql"})
    public void testGetByGoodsId() throws Exception {
        Long goodsId = 1L;
        GoodsDetailDO expectedGoodsDetail = createGoodsDetail(goodsId);
        expectedGoodsDetail.setDetailContent(null);
        GoodsDetailDO actualGoodsDetail = goodsDetailDAO.getByGoodsId(goodsId);
        Assert.assertEquals(expectedGoodsDetail, actualGoodsDetail);
    }

    /**
     * 测试新建商品
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Long goodsId = 1L;
        GoodsDetailDO goodsDetail = createGoodsDetail(goodsId);
        Assert.assertNotNull(goodsDetail.getId());
        Assert.assertThat(goodsDetail.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试更新商品
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        Long goodsId = 1L;
        GoodsDetailDO expectedGoodsDetail = createGoodsDetail(goodsId);

        expectedGoodsDetail.setDetailContent("修改后的" + expectedGoodsDetail.getDetailContent());
        goodsDetailDAO.update(expectedGoodsDetail);

        GoodsDetailDO actualGoodsDetail = goodsDetailDAO.getByGoodsId(expectedGoodsDetail.getGoodsId());

        Assert.assertEquals(expectedGoodsDetail, actualGoodsDetail);
    }

    /**
     * 测试删除商品
     *
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception {
        Long goodsId = 1L;
        GoodsDetailDO expectedGoodsDetail = createGoodsDetail(goodsId);

        goodsDetailDAO.remove(expectedGoodsDetail.getId());

        GoodsDetailDO actualGoodsDetail = goodsDetailDAO.getByGoodsId(expectedGoodsDetail.getGoodsId());

        Assert.assertNull(actualGoodsDetail);
    }

    /**
     * 创建商品
     *
     * @return 商品
     * @throws Exception
     */
    private GoodsDetailDO createGoodsDetail(Long goodsId) throws Exception {
        GoodsDetailDO goodsDetail = new GoodsDetailDO();

        goodsDetail.setGoodsId(goodsId);
        goodsDetail.setDetailContent("测试商品详情内容");
        goodsDetail.setGmtCreate(dateProvider.getCurrentTime());
        goodsDetail.setGmtModified(dateProvider.getCurrentTime());

        goodsDetailDAO.save(goodsDetail);

        return goodsDetail;
    }
}