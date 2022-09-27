package com.zpl.eshop.comment.dao;


import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.common.util.DateProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * 评论统计管理模块的DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class CommentAggregateDAOTest {

    /**
     * 评论统计管理模块的DAO组件
     */
    @Autowired
    private CommentAggregateDAO commentAggregateDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增评论统计信息
     *
     * @throws Exception
     */
    @Test
    public void testSaveCommentAggregate() throws Exception {
        Long goodsId = 1L;
        CommentAggregateDO commentAggregateDO = createCommentAggregateDO(goodsId);
        assertNotNull(commentAggregateDO.getId());
        assertThat(commentAggregateDO.getId(), greaterThan(0L));
    }

    /**
     * 测试根据商品id查询评论统计信息
     *
     * @throws Exception
     */
    @Test
    public void testGetCommentAggregateByGoodsId() throws Exception {
        Long goodsId = 1L;
        CommentAggregateDO commentAggregateDO = createCommentAggregateDO(goodsId);
        CommentAggregateDO resultCommentAggregateDO =
                commentAggregateDAO.getCommentAggregateByGoodsId(goodsId);
        Assert.assertEquals(commentAggregateDO, resultCommentAggregateDO);
    }

    /**
     * 测试更新评论统计信息
     *
     * @throws Exception
     */
    @Test
    public void testUpdateCommentAggregate() throws Exception {
        Long goodsId = 1L;
        CommentAggregateDO commentAggregateDO = createCommentAggregateDO(goodsId);

        commentAggregateDO.setGoodCommentCount(5L);
        commentAggregateDAO.updateCommentAggregate(commentAggregateDO);

        CommentAggregateDO resultCommentAggregateDO =
                commentAggregateDAO.getCommentAggregateByGoodsId(goodsId);

        Assert.assertEquals(commentAggregateDO, resultCommentAggregateDO);
    }

    /**
     * 创建评论统计DO对象
     *
     * @return 评论统计DO对象
     * @throws Exception
     */
    private CommentAggregateDO createCommentAggregateDO(Long goodsId) throws Exception {
        CommentAggregateDO commentAggregate = new CommentAggregateDO();
        commentAggregate.setBadCommentCount(1L);
        commentAggregate.setGmtCreate(dateProvider.getCurrentTime());
        commentAggregate.setGmtModified(dateProvider.getCurrentTime());
        commentAggregate.setGoodCommentCount(1L);
        commentAggregate.setGoodCommentRate(1.0);
        commentAggregate.setGoodsId(goodsId);
        commentAggregate.setMediumCommentCount(1L);
        commentAggregate.setShowPictureCommentCount(1L);
        commentAggregate.setTotalCommentCount(5L);

        commentAggregateDAO.saveCommentAggregate(commentAggregate);

        return commentAggregate;
    }

}
