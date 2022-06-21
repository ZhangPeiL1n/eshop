package com.zpl.eshop.auth.mapper;

import com.zpl.eshop.auth.domain.AccountDO;
import com.zpl.eshop.auth.domain.AccountQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 帐号管理 mapper 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/28 15:47
 **/
@Mapper
public interface AccountMapper {


    /**
     * 分页查询帐号
     *
     * @param query 查询条件
     * @return 帐号
     */
    @Select("<script>" +
            "select " +
            "id," +
            "username," +
            "name," +
            "remark," +
            "gmt_create," +
            "gmt_modified " +
            "from auth_account a," +
            "(" +
            "select id " +
            "from auth_account " +
            "where 1 = 1" +

            "<if test='username != null'>" +
            "and username like '${username}%'" +
            "</if>" +

            "<if test='name != null'>" +
            "and name like '${name}%'" +
            "</if>" +

            "limit #{offsite},#{size}" +
            ") b " +
            "where a.id = b.id" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "username", property = "username"),
            @Result(column = "name", property = "name"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<AccountDO> listByPage(AccountQuery query);


    /**
     * 新增帐号
     *
     * @param account 帐号
     */
    @Insert("insert into auth_account(" +
            "username, name, password, remark, gmt_create, gmt_modified" +
            ") values(" +
            "#{username}," +
            "#{name}," +
            "#{password}," +
            "#{remark}," +
            "#{gmt_create}," +
            "#{gmt_modified}" +
            ") ")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(AccountDO account);

    /**
     * 根据id查询帐号
     *
     * @param id 帐号id
     * @return 帐号
     */
    @Select("select " +
            "id," +
            "username," +
            "name," +
            "remark," +
            "gmt_create," +
            "gmt_modified " +
            "from auth_account " +
            "where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "username", property = "username"),
            @Result(column = "name", property = "name"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    AccountDO getById(@Param("id") Long id);


    /**
     * 更新帐号信息
     *
     * @param account 帐号信息
     */
    @Update("update auth_account set " +
            "password = #{passowrd}," +
            "remark = #{remark}," +
            "gmt_modified = #{gmtModified}")
    void update(AccountDO account);

    /**
     * 根据id删除帐号
     *
     * @param id 帐号id
     */
    @Delete("delete from auth_account where id = #{id}")
    void remove(@Param("id") Long id);
}
