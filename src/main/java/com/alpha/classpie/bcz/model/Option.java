package com.alpha.classpie.bcz.model;


import java.util.Objects;
import java.util.UUID;

/**
 * 背单词的选择对象
 */

public class Option {
    String id=null;
    String imagePath=null;
    String label=null;

    public Option(String imagePath, String label) {
        this.imagePath = imagePath;
        this.label = label;
        this.id= UUID.randomUUID().toString();
    }

    public Option() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Option{" +
                "imagePath='" + imagePath + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id.equals(option.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
