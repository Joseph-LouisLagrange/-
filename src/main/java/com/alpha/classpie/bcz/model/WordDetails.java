package com.alpha.classpie.bcz.model;

import com.alpha.classpie.bcz.model.GraphicExampleSentences;
import com.alpha.classpie.bcz.model.Translation;
import com.alpha.classpie.bcz.model.WordPhoneticTranscription;

public class WordDetails {
    private Integer id;

    private String word;

    private String rootAffix;

    private String pictogramImagePath;


    public WordDetails(Integer id, String word, String rootAffix, String pictogramImagePath) {
        this.id = id;
        this.word = word;
        this.rootAffix = rootAffix;
        this.pictogramImagePath = pictogramImagePath;

    }

    public WordDetails() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getRootAffix() {
        return rootAffix;
    }

    public void setRootAffix(String rootAffix) {
        this.rootAffix = rootAffix;
    }

    public String getPictogramImagePath() {
        return pictogramImagePath;
    }

    public void setPictogramImagePath(String pictogramImagePath) {
        this.pictogramImagePath = pictogramImagePath;
    }
}
