package com.alpha.classpie.dao;


import java.util.List;

import com.alpha.classpie.dto.exception.ExceptionDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExceptionDtoMapper {

    List<ExceptionDto> getAll();

}
