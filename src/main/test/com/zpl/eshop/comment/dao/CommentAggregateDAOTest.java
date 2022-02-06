package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.common.util.DateProvider;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * 评论统计信息管理模块DAO组件单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/3 17:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CommentAggregateDAOTest {

    @Autowired
    private CommentAggregateDAO commentAggregateDAO;

    @Autowired
    private DateProvider dateProvider;


    /**
     * 测试新增评论统计信息
     */
    @Test
    public void testSaveCommentAggregate() throws Exception {
        Long goodsId = 1L;
        CommentAggregateDO commentAggregateDO = createCommentAggregateDO(goodsId);
        assertNotNull(commentAggregateDO.getId());
        assertThat(commentAggregateDO.getId(), Matchers.greaterThan(0L));

    }

    /**
     * 测试根据商品 id 查询评论统计信息
     */
    @Test
    public void testGetCommentAggregateByGoodsId() throws Exception {
        Long goodsId = 1L;
        CommentAggregateDO commentAggregateDO = createCommentAggregateDO(goodsId);
        CommentAggregateDO resultCommentAggregate = commentAggregateDAO.getCommentAggregateByGoodsId(goodsId);
        assertNotNull(resultCommentAggregate);
        assertEquals(commentAggregateDO, resultCommentAggregate);
    }

    /**
     * 测试更新评论统计信息
     */
    @Test
    public void testUpdateCommentAggregate() throws Exception {
        Long goodsId = 1L;
        CommentAggregateDO commentAggregateDO = createCommentAggregateDO(goodsId);
        commentAggregateDAO.updateCommentAggregate(commentAggregateDO);
        CommentAggregateDO resultCommentAggregate = commentAggregateDAO.getCommentAggregateByGoodsId(commentAggregateDO.getGoodsId());
        assertNotNull(resultCommentAggregate);
        assertEquals(commentAggregateDO, resultCommentAggregate);
    }

    /**
     * 创建评论统计DO对象
     *
     * @return DO对象
     */
    private CommentAggregateDO createCommentAggregateDO(Long goodsId) throws Exception {
        CommentAggregateDO commentAggregateDO = new CommentAggregateDO();
        commentAggregateDO.setGoodsId(goodsId);
        commentAggregateDO.setTotalCommentCount(1L);
        commentAggregateDO.setGoodCommentCount(1L);
        commentAggregateDO.setGoodCommentRate(0.33);
        commentAggregateDO.setShowPicturesCommentCount(1L);
        commentAggregateDO.setMediumCommentCount(1L);
        commentAggregateDO.setBadCommentCount(1L);
        commentAggregateDO.setGmtCreate(dateProvider.getCurrentTime());
        commentAggregateDO.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateDAO.saveCommentAggregate(commentAggregateDO);
        return commentAggregateDO;
    }

}
