package com.alpha.classpie.serviceTest;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.service.impl.SimpleUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@SpringBootTest
public class SimpleUserServiceTest {


    //String name, String school, String accountNumber, String emailNumber, String telephoneNumber, String password, String studentId
    @Nested
    @SpringBootTest
    public static class StudentServiceTest{

        @Resource(name = "studentService")
        public SimpleUserService studentUserService;

        @ParameterizedTest
        @CsvSource({"下递,杰克大学,118oiu,1593684@qq.com,15826173197,asdasd,4444488888"})
        public void register(String name, String school, String accountNumber, String emailNumber, String telephoneNumber, String password, String studentId) throws ExceptionDto {
            Student student=new Student(null,name,school,accountNumber,emailNumber,telephoneNumber,password,studentId);
            Assertions.assertDoesNotThrow(()->{studentUserService.smsRegister(student,2206);});
           // Assertions.assertNull(studentUserService.register(student,5555));
        }
    }
}
