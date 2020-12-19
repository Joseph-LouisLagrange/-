package com.alpha.classpie.serviceTest;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.service.impl.UserServiceImpl;
import com.alpha.classpie.service.inf.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @ParameterizedTest
    @CsvSource("1,123456")
    public void checkPasswordTest(int userId,String password){
        boolean b = userService.checkPassword(userId, password);
        Assertions.assertTrue(b);
    }

    public void Test(){

    }

}
