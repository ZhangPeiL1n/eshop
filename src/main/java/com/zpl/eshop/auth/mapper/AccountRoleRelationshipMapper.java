package com.zpl.eshop.auth.mapper;

import com.zpl.eshop.auth.domain.AccountRoleRelationshipDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 帐号角色关系Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 18:31
 **/
@Mapper
public interface AccountRoleRelationshipMapper {

    /**
     * 查询角色关联的帐号记录数
     *
     * @param roleId 角色id
     * @return 记录数
     */
    @Select("SELECT count(*) " +
            "FROM auth_account_role_relationship " +
            "WHERE role_id = #{roleId}")
    Long countByRoleId(@Param("roleId") Long roleId);

    /**
     * 新增帐号和角色关联关系
     *
     * @param relation 关系
     */
    @Insert("insert into auth_account_role_relationship(" +
            "account_id, role_id, gmt_create, gmt_modified" +
            ") values (" +
            "#{accountId}," +
            "#{roleId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ") ")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(AccountRoleRelationshipDO relation);

    /**
     * 根据帐号id查询帐号和角色关联关系
     *
     * @param accountId 帐号id
     * @return 帐号和角色关联关系
     */
    @Select("select " +
            "id," +
            "account_id," +
            "role_id," +
            "gmt_create," +
            "gmt_modified " +
            "from auth_account_role_relationship " +
            "where account_id = #{accountId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "account_id", property = "accountId"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmtModified", property = "gmtModified")
    })
    List<AccountRoleRelationshipDO> listByAccountId(@Param("accountId") Long accountId);


    /**
     * 根据帐号id删除帐号角色关联关系
     *
     * @param accountId 帐号id
     */
    @Delete("delete from auth_account_role_relationship where account_id = #{accountId}")
    void removeByAccountId(@Param("accountId") Long accountId);
}
