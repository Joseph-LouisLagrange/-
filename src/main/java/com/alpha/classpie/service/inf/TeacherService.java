package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.user.Teacher;
import org.springframework.stereotype.Service;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Service
public interface TeacherService {
    public Teacher getOneById(int id);
    public Teacher register(Teacher teacher);
}
