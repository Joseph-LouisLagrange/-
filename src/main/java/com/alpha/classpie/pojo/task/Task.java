package com.alpha.classpie.pojo.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private Integer id;

    private Integer courseId;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp deadline;

    private Integer fullMarks;

    private Boolean isCheckDuplicate;

    private Integer promulgator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp releaseTime;

    private Boolean strict;

    private String resume;

    private TaskCheckDuplicate taskCheckDuplicate;

    public Task(Integer id, Integer courseId, String name, Timestamp deadline, Integer fullMarks, Boolean isCheckDuplicate, Integer promulgator, Timestamp releaseTime, Boolean strict, String resume) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.deadline = deadline;
        this.fullMarks = fullMarks;
        this.isCheckDuplicate = isCheckDuplicate;
        this.promulgator = promulgator;
        this.releaseTime = releaseTime;
        this.strict = strict;
        this.resume = resume;
    }

    public Task(Integer id, Integer courseId, String name, Timestamp deadline, Integer fullMarks, Boolean isCheckDuplicate, Integer promulgator, Timestamp releaseTime, Boolean strict) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.deadline = deadline;
        this.fullMarks = fullMarks;
        this.isCheckDuplicate = isCheckDuplicate;
        this.promulgator = promulgator;
        this.releaseTime = releaseTime;
        this.strict = strict;
    }

    public Task() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(Integer fullMarks) {
        this.fullMarks = fullMarks;
    }

    public Boolean getIsCheckDuplicate() {
        return isCheckDuplicate;
    }

    public void setIsCheckDuplicate(Boolean isCheckDuplicate) {
        this.isCheckDuplicate = isCheckDuplicate;
    }

    public Integer getPromulgator() {
        return promulgator;
    }

    public void setPromulgator(Integer promulgator) {
        this.promulgator = promulgator;
    }


    public Boolean getStrict() {
        return strict;
    }

    public void setStrict(Boolean strict) {
        this.strict = strict;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
