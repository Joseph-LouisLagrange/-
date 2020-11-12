package com.alpha.classpie.pojo.course;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class Course {
    private Integer id;

    private Integer adminTeacherId;

    private String code;

    private String name;

    private String className;

    private Date termYear;

    private Integer semester;

    private Boolean isArchive;



    public Course(Integer id, Integer adminTeacherId, String code, String name, String className, Date termYear, Integer semester, Boolean isArchive) {
        this.id = id;
        this.adminTeacherId = adminTeacherId;
        this.code = code;
        this.name = name;
        this.className = className;
        this.termYear = termYear;
        this.semester = semester;
        this.isArchive = isArchive;
    }

    public Course() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminTeacherId() {
        return adminTeacherId;
    }

    public void setAdminTeacherId(Integer adminTeacherId) {
        this.adminTeacherId = adminTeacherId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }



    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }
}
