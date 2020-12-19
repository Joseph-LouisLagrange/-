package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.user.Teacher;
import org.springframework.stereotype.Service;


@Service
public interface TeacherService {
    public Teacher getOneById(int id);
    public Teacher register(Teacher teacher);
}
