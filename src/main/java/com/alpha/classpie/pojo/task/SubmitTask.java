package com.alpha.classpie.pojo.task;

public class SubmitTask extends SubmitTaskKey {
    private String state;

    private Integer similarity;

    private String submitState;

    private Integer score;

    private Long wordCount;

    public SubmitTask(Integer userId, Integer taskId, String state, Integer similarity, String submitState, Integer score, Long wordCount) {
        super(userId, taskId);
        this.state = state;
        this.similarity = similarity;
        this.submitState = submitState;
        this.score = score;
        this.wordCount = wordCount;
    }

    public SubmitTask() {
        super();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }

    public String getSubmitState() {
        return submitState;
    }

    public void setSubmitState(String submitState) {
        this.submitState = submitState;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }
}
