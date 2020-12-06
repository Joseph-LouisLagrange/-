package com.alpha.classpie.bcz.model;

public class WordPhoneticTranscription {
    private Integer wordDetailsId;

    private String phoneticTranscription;

    public WordPhoneticTranscription(Integer wordDetailsId, String phoneticTranscription) {
        this.wordDetailsId = wordDetailsId;
        this.phoneticTranscription = phoneticTranscription;
    }

    public WordPhoneticTranscription() {
        super();
    }

    public Integer getWordDetailsId() {
        return wordDetailsId;
    }

    public void setWordDetailsId(Integer wordDetailsId) {
        this.wordDetailsId = wordDetailsId;
    }

    public String getPhoneticTranscription() {
        return phoneticTranscription;
    }

    public void setPhoneticTranscription(String phoneticTranscription) {
        this.phoneticTranscription = phoneticTranscription;
    }
}
