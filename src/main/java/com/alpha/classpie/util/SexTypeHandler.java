package com.alpha.classpie.util;

import com.alpha.classpie.type.Sex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


@Component
@MappedTypes(Sex.class)
public class SexTypeHandler implements TypeHandler<Sex> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Sex sex, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,sex.getSexValue());
    }

    private Sex toSex(String s){
        return Arrays.stream(Sex.values()).filter(sex -> sex.getSexValue().equals(s)).findFirst().get();
    }

    @Override
    public Sex getResult(ResultSet resultSet, String s) throws SQLException {
        return toSex(resultSet.getString(s));
    }

    @Override
    public Sex getResult(ResultSet resultSet, int i) throws SQLException {
        return toSex(resultSet.getString(i));
    }

    @Override
    public Sex getResult(CallableStatement callableStatement, int i) throws SQLException {
        return toSex(callableStatement.getString(i));
    }
}
