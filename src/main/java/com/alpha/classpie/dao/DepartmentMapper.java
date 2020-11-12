package com.alpha.classpie.dao;

import com.alpha.classpie.dto.DepartmentTree;
import com.alpha.classpie.pojo.other.Department;
import com.alpha.classpie.example.DepartmentExample;
import java.util.List;
import java.util.Set;

import com.alpha.classpie.pojo.other.EducationLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DepartmentMapper {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    Set<DepartmentTree> getDepartmentTreeByEducationId(int educationId);
}
