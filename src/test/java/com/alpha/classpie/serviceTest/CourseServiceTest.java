package com.alpha.classpie.serviceTest;

import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.service.inf.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author 杨能
 * @create 2020/11/9
 */
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @ParameterizedTest
    @CsvSource(value = "1,1256sss,物理上方,大班级,2019-01-01,1,false",delimiter = ',')
    public void createCourseTest(Integer adminTeacherId, String code, String name, String className, LocalDate termYear, Integer semester, Boolean isArchive){
        Course course = new Course(0,adminTeacherId,code,name,className, Date.valueOf(termYear),semester,isArchive);
        Assertions.assertDoesNotThrow(()->{courseService.createCourse(course,1);});
    }

    @ParameterizedTest
    @CsvSource(value = "5,1,123456")
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
    @ValueSource(ints = {1})
    public void getTeachCoursesTest(int userId){
        org.assertj.core.api.Assertions.assertThat(courseService.getTeachCourses(userId))
                .isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void getLearnCoursesTest(int userId){
        org.assertj.core.api.Assertions.assertThat(courseService.getLearnCourses(userId))
                .isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {6})
    public void archiveCourseTest(int courseId){
        Assertions.assertTrue(courseService.archiveCourse(courseId));
    }

    @ParameterizedTest
    @CsvSource("1256sss,10")
    public void enterCourseTest(String courseCode,int userId){
        Assertions.assertNotNull(courseService.enterCourse(courseCode, userId));
    }

    @ParameterizedTest
    @CsvSource("1,6")
    public void archiveOwnCourseTest(int userId,int courseId){
        Assertions.assertTrue(courseService.archiveOwnCourse(userId, courseId));
    }

    @ParameterizedTest
    @CsvSource(value = "6,1,1256ssrxf,物理,笑班级,2019-01-01,1,false",delimiter = ',')
    public void editCourseTest(int courseId,Integer adminTeacherId, String code, String name, String className, LocalDate termYear, Integer semester, Boolean isArchive){
        Course course = new Course(0,adminTeacherId,code,name,className, Date.valueOf(termYear),semester,isArchive);
        Assertions.assertNotNull(courseService.editCourse(course,courseId));
    }

}
