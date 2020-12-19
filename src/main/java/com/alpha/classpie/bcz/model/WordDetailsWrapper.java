package com.alpha.classpie.bcz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class WordDetailsWrapper extends WordDetails {
    List<String> phoneticTranscriptions;//音标
    List<String> translations;//中文翻译

    //英文释义
    List<String> englishInterpretation=null;

    //图文例句
    GraphicExampleSentences graphicExampleSentences;
}
