package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentPictureDO;
import com.zpl.eshop.common.util.DateProvider;
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
 * 评论晒图管理模块DAO组件单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/3 17:43
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CommentPictureDAOTest {

    @Autowired
    private CommentPictureDAO commentPictureDAO;

    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增评论晒图
     */
    @Test
    public void testSaveCommentPicture() throws Exception {
        CommentPictureDO commentPictureDO = createCommentPictureDO();
        commentPictureDAO.saveCommentPicture(commentPictureDO);
        assertNotNull(commentPictureDO.getId());
        assertThat(commentPictureDO.getId(), greaterThan(0L));
    }

    /**
     * 创建评论晒图DO对象
     *
     * @return DO
     * @throws Exception
     */
    private CommentPictureDO createCommentPictureDO() throws Exception {
        CommentPictureDO commentPictureDO = new CommentPictureDO();
        commentPictureDO.setCommentInfoId(1L);
        commentPictureDO.setCommentPicturePath("/test");
        commentPictureDO.setGmtCreate(dateProvider.getCurrentTime());
        commentPictureDO.setGmtModified(dateProvider.getCurrentTime());
        return commentPictureDO;
    }
}
