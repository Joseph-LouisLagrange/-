package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.user.Permission;
import com.alpha.classpie.example.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer code);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExampleWithBLOBs(PermissionExample example);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer code);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExampleWithBLOBs(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKeyWithBLOBs(Permission record);

    int updateByPrimaryKey(Permission record);
}
