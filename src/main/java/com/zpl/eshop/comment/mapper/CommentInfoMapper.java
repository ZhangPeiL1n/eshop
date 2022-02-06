package com.zpl.eshop.comment.mapper;

import com.zpl.eshop.comment.domain.CommentInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 评论信息管理模块的mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:14
 **/
@Mapper
public interface CommentInfoMapper {

    /**
     * 新增评论信息
     *
     * @param commentInfoDO 评论信息对象
     */
    @Insert("INSERT INTO comment_info(" +
            "user_account_id," +
            "username," +
            "order_info_id," +
            "order_item_id," +
            "goods_id," +
            "goods_sku_id," +
            "goods_sku_sale_properties," +
            "total_score," +
            "goods_score," +
            "customer_service_score," +
            "logistics_score," +
            "comment_content," +
            "is_show_pictures," +
            "is_default_comment," +
            "comment_status," +
            "comment_type," +
            "gmt_create," +
            "gmt_modified" +
            ")" +
            "VALUES(" +
            "#{userAccountId}," +
            "#{username}," +
            "#{orderInfoId}," +
            "#{orderItemId}," +
            "#{goodsId}," +
            "#{goodsSkuId}," +
            "#{goodsSkuSaleProperties}," +
            "#{totalScore}," +
            "#{goodsScore}," +
            "#{customerServiceScore}," +
            "#{logisticsScore}," +
            "#{commentContent}," +
            "#{showPicture}," +
            "#{defaultComment}," +
            "#{commentStatus}," +
            "#{commentType}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveCommentInfo(CommentInfoDO commentInfoDO);
}
