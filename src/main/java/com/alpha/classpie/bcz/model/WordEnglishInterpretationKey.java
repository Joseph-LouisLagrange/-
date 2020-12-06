package com.alpha.classpie.bcz.model;

public class WordEnglishInterpretationKey {
    private Integer wordId;

    private String englishInterpretation;

    public WordEnglishInterpretationKey(Integer wordId, String englishInterpretation) {
        this.wordId = wordId;
        this.englishInterpretation = englishInterpretation;
    }

    public WordEnglishInterpretationKey() {
        super();
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getEnglishInterpretation() {
        return englishInterpretation;
    }

    public void setEnglishInterpretation(String englishInterpretation) {
        this.englishInterpretation = englishInterpretation;
    }
}
