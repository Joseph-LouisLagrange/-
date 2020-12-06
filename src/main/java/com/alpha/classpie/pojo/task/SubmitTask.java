package com.alpha.classpie.pojo.task;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;


public class SubmitTask extends SubmitTaskKey {
    private Boolean isCorrect;

    private Integer similarity;

    private Boolean isOnTime;

    private Float score;

    private Long wordCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd",timezone = "GMT+8")
    private Timestamp datetime;

    public SubmitTask(Integer userId, Integer taskId, Boolean isCorrect, Integer similarity, Boolean isOnTime, Float score, Long wordCount,  Timestamp  datetime) {
        super(userId, taskId);
        this.isCorrect = isCorrect;
        this.similarity = similarity;
        this.isOnTime = isOnTime;
        this.score = score;
        this.wordCount = wordCount;
        this.datetime = datetime;
    }

    public SubmitTask() {
        super();
    }


    public SubmitTask(Integer userId, Integer taskId) {
        super(userId, taskId);
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }

    public Boolean getIsOnTime() {
        return isOnTime;
    }

    public void setIsOnTime(Boolean isOnTime) {
        this.isOnTime = isOnTime;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public  Timestamp  getDatetime() {
        return datetime;
    }

    public void setDatetime( Timestamp  datetime) {
        this.datetime = datetime;
    }
}
