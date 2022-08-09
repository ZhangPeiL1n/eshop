package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.BrandDO;
import com.zpl.eshop.commodity.domain.BrandQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/8/8 21:32
 **/
@Mapper
public interface BrandMapper {

    /**
     * 分页查询品牌
     *
     * @param query 分页查询条件
     * @return
     */
    @Select("<script>" +
            "select " +
            "a.id," +
            "a.chinese_name," +
            "a.english_name," +
            "a.alias_name," +
            "a.logo_path," +
            "a.introduction," +
            "a.auth_voucher_path," +
            "a.location," +
            "a.remark," +
            "a.gmt_create," +
            "a.gmt_modified " +
            "from commodity_brand a," +
            "(" +
            "select id " +
            "from commodity_brand " +
            "where 1=1 " +
            "<if test='chineseName != null'>" +
            "and chinese_name like '${chineseName}%'" +
            "</if>" +
            "<if test='englishName != null'>" +
            "and english_name like '${englishName}%'" +
            "</if>" +
            "<if test='aliasName != null'>" +
            "and alias_name like '${aliasName}%'" +
            "</if>" +
            "limit #{offset},#{size}" +
            ") b " +
            "where a.id = b.id" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "chinese_name", property = "chineseName"),
            @Result(column = "english_name", property = "englishName"),
            @Result(column = "alias_name", property = "aliasName"),
            @Result(column = "logo_path", property = "logoPath"),
            @Result(column = "introduction", property = "introduction"),
            @Result(column = "auth_voucher_path", property = "authVoucherPath"),
            @Result(column = "location", property = "location"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<BrandDO> listByPage(BrandQuery query);


    /**
     * 根据id查品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    @Select("select " +
            "a.id," +
            "a.chinese_name," +
            "a.english_name," +
            "a.alias_name," +
            "a.logo_path," +
            "a.introduction," +
            "a.auth_voucher_path," +
            "a.location," +
            "a.remark," +
            "a.gmt_create," +
            "a.gmt_modified " +
            "from commodity_brand a " +
            "where a.id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "chinese_name", property = "chineseName"),
            @Result(column = "english_name", property = "englishName"),
            @Result(column = "alias_name", property = "aliasName"),
            @Result(column = "logo_path", property = "logoPath"),
            @Result(column = "introduction", property = "introduction"),
            @Result(column = "auth_voucher_path", property = "authVoucherPath"),
            @Result(column = "location", property = "location"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    BrandDO getById(@Param("id") Long id);

    /**
     * 新增品牌
     *
     * @param brand 品牌
     */
    @Insert("INSERT INTO commodity_brand(chinese_name, english_name, alias_name, logo_path, introduction, auth_voucher_path, location, remark, gmt_create, gmt_modified) " +
            "values (#{chineseName},#{englishName},#{aliasName},#{logoPath},#{introduction},#{authVoucherPath},#{location},#{remark},#{gmtCreate},#{gmtModified})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(BrandDO brand);

    /**
     * 更新品牌
     *
     * @param brand 品牌
     */
    @Update("<script>" +
            "UPDATE commodity_brand set " +
            "<if test='#{chineseName} != null'>" +
            "chinese_name = #{chineseName}," +
            "</if>" +
            "<if test='#{englishName} != null'>" +
            "english_name = #{englishName}," +
            "</if>" +
            "<if test='#{aliasName} != null'>" +
            "alias_name = #{aliasName}," +
            "</if>" +
            "<if test='#{logoPath} != null'>" +
            "logo_path = #{logoPath}," +
            "</if>" +
            "<if test='#{introduction} != null'>" +
            "introduction = #{introduction}," +
            "</if>" +
            "<if test='#{authVoucherPath} != null'>" +
            "auth_voucher_path = #{authVoucherPath}," +
            "</if>" +
            "<if test='#{location} != null'>" +
            "location = #{location}," +
            "</if>" +
            "<if test='#{remark} != null'>" +
            "remark = #{remark}," +
            "</if>" +
            "gmt_modified = #{gmtModified} " +
            "where id = #{id}" +
            "</script>")
    void update(BrandDO brand);

    /**
     * 删除品牌
     *
     * @param id 品牌id
     */
    @Delete("delete from commodity_brand where id = #{id}")
    void remove(@Param("id") Long id);
}
