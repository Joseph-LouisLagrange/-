package com.alpha.classpie.serviceTest;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.service.inf.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/9
 */
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    CourseService courseService;

    ObjectMapper objectMapper=new ObjectMapper();

    @ParameterizedTest
    @CsvSource(value = "1,1256sss,物理上方,大班级,2019-01-01,1,false",delimiter = ',')
    public void createCourseTest(Integer adminTeacherId, String code, String name, String className, LocalDate termYear, Integer semester, Boolean isArchive){
        Course course = new Course(0,adminTeacherId,code,name,className, Date.valueOf(termYear),semester,isArchive,1);
        Assertions.assertDoesNotThrow(()->{courseService.createCourse(course,1);});
    }

    @ParameterizedTest
    @CsvSource(value = "9,1,123456")
    public void deleteCourseTest1(int courseId,int userId,String password){
        Assertions.assertTrue(courseService.deleteCourse(courseId, userId, password));
    }

    @ParameterizedTest
    @CsvSource(value = "4,1,1234567")
    public void deleteCourseTest2(int courseId,int userId,String password){
        Assertions.assertFalse(courseService.deleteCourse(courseId, userId, password));
    }

    public void test(){

    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void getStudentsTest(int courseId){
        courseService.getStudents(courseId).forEach(System.out::println);
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void getTeachersTest(int courseId){
        Gson gson = new Gson();
        courseService.getTeachers(courseId).forEach(System.out::println);
    }


    @ParameterizedTest
    @ValueSource(ints = {1})
    public void getTeachCoursesTest(int userId){
        courseService.getTeachCourses(userId).stream().map(course -> dataWrapper.doCourseWrapper(course,userId)).forEach(courseWrapper -> {
            try {
                System.out.println(objectMapper.writeValueAsString(courseWrapper));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void getLearnCoursesTest(int userId){
        org.assertj.core.api.Assertions.assertThat(courseService.getLearnCourses(userId))
                .isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void archiveCourseTest(int courseId){
        Assertions.assertTrue(courseService.archiveCourse(courseId));
    }

    @ParameterizedTest
    @CsvSource(value = {"ia8o65,10","ia8o65,15"})
    public void enterCourseTest(String courseCode,int userId){
        Assertions.assertNotNull(courseService.enterCourse(courseCode, userId));
    }

    @ParameterizedTest
    @CsvSource("1,10")
    public void archiveOwnCourseTest(int userId,int courseId){
        Assertions.assertTrue(courseService.archiveOwnCourse(userId, courseId));
    }

    @ParameterizedTest
    @CsvSource(value = "10,1,1256ssrxf,物理,笑班级,2019-01-01,1,false",delimiter = ',')
    public void editCourseTest(int courseId,Integer adminTeacherId, String code, String name, String className, LocalDate termYear, Integer semester, Boolean isArchive){
        Course course = new Course(0,adminTeacherId,code,name,className, Date.valueOf(termYear),semester,isArchive,1);
        Assertions.assertNotNull(courseService.editCourse(course,courseId));
    }
    @Autowired
    DataWrapper dataWrapper;

    @ParameterizedTest
    @ValueSource(ints = {1})
    public void getTeachCourses(int id){
      courseService.getTeachCourses(id)
                .stream().map(course -> dataWrapper.doCourseWrapper(course,id))
                .collect(Collectors.toList());
    }
}
