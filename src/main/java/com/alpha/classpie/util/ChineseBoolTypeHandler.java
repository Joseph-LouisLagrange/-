package com.alpha.classpie.util;

import com.alpha.classpie.type.ChineseBool;
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
import java.util.function.Predicate;


@Component
@MappedTypes(ChineseBool.class)
public class ChineseBoolTypeHandler implements TypeHandler<ChineseBool> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, ChineseBool chineseBool, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,chineseBool.getValue());
    }

    @Override
    public ChineseBool getResult(ResultSet resultSet, String s) throws SQLException {
        return Arrays.stream(ChineseBool.values()).filter(Predicate.isEqual(resultSet.getString(s))).findFirst().get();
    }

    @Override
    public ChineseBool getResult(ResultSet resultSet, int i) throws SQLException {
        return Arrays.stream(ChineseBool.values()).filter(Predicate.isEqual(resultSet.getString(i))).findFirst().get();
    }

    @Override
    public ChineseBool getResult(CallableStatement callableStatement, int i) throws SQLException {
        return Arrays.stream(ChineseBool.values()).filter(Predicate.isEqual(callableStatement.getString(i))).findFirst().get();
    }
}
