package com.alpha.classpie.bcz;

import com.alpha.classpie.bcz.model.WordDetails;
import com.alpha.classpie.bcz.model.WordDetailsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WordDetailsMapper {
    long countByExample(WordDetailsExample example);

    int deleteByExample(WordDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WordDetails record);

    int insertSelective(WordDetails record);

    List<WordDetails> selectByExample(WordDetailsExample example);

    WordDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WordDetails record, @Param("example") WordDetailsExample example);

    int updateByExample(@Param("record") WordDetails record, @Param("example") WordDetailsExample example);

    int updateByPrimaryKeySelective(WordDetails record);

    int updateByPrimaryKey(WordDetails record);
}
