package com.alpha.classpie.bcz.model;

public class Translation {
    private Integer wordDetailsId;

    private String translation;

    public Translation(Integer wordDetailsId, String translation) {
        this.wordDetailsId = wordDetailsId;
        this.translation = translation;
    }

    public Translation() {
        super();
    }

    public Integer getWordDetailsId() {
        return wordDetailsId;
    }

    public void setWordDetailsId(Integer wordDetailsId) {
        this.wordDetailsId = wordDetailsId;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
