package com.alpha.classpie.bcz.model;

public class GraphicExampleSentences {
    private Integer id;

    private String exampleSentences;

    private String translation;

    private String imagePath;

    private String keyWord;

    private Integer wordDetailsId;

    public GraphicExampleSentences(Integer id, String exampleSentences, String translation, String imagePath, String keyWord, Integer wordDetailsId) {
        this.id = id;
        this.exampleSentences = exampleSentences;
        this.translation = translation;
        this.imagePath = imagePath;
        this.keyWord = keyWord;
        this.wordDetailsId = wordDetailsId;
    }

    public GraphicExampleSentences() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExampleSentences() {
        return exampleSentences;
    }

    public void setExampleSentences(String exampleSentences) {
        this.exampleSentences = exampleSentences;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getWordDetailsId() {
        return wordDetailsId;
    }

    public void setWordDetailsId(Integer wordDetailsId) {
        this.wordDetailsId = wordDetailsId;
    }
}
