package com.alpha.classpie.dao;

import com.alpha.classpie.example.TeacherDepartmentExample;
import com.alpha.classpie.pojo.other.Department;
import com.alpha.classpie.pojo.user.TeacherDepartment;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherDepartmentMapper {
    List<Department> getDepartmentsByUserId(int userId);

    long countByExample(TeacherDepartmentExample example);

    int deleteByExample(TeacherDepartmentExample example);

    int deleteByPrimaryKey(TeacherDepartment key);

    int insert(TeacherDepartment record);

    int insertSelective(TeacherDepartment record);

    List<TeacherDepartment> selectByExample(TeacherDepartmentExample example);

    int updateByExampleSelective(@Param("record") TeacherDepartment record, @Param("example") TeacherDepartmentExample example);

    int updateByExample(@Param("record") TeacherDepartment record, @Param("example") TeacherDepartmentExample example);
}
