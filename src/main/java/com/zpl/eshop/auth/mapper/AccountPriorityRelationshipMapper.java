package com.zpl.eshop.auth.mapper;

import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:05
 **/
@Mapper
public interface AccountPriorityRelationshipMapper {


    /**
     * 新增帐号权限关联关系
     *
     * @param accountPriorityRelationshipDO 帐号权限关联关系DO
     */
    @Insert("INSERT INTO auth_account_priority_relationship(" +
            "account_id," +
            "priority_id," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{accountId}," +
            "#{priorityId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) " +
            "FROM auth_account_priority_relationship " +
            "WHERE priority_id = #{priorityId}")
    Long countByPriorityId(@Param("priorityId") Long priorityId);
}
