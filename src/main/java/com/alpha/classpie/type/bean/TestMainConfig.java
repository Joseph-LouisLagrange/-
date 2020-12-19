package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.*;

import java.time.LocalDateTime;


public class TestMainConfig {
    boolean isCreditFraction;
    ProblemUpset problemUpset;
    OptionUpset optionUpset;
    AnswerAnnouncement answerAnnouncement;
    GradeAnnouncement gradeAnnouncement;
    MultipleChoice multipleChoice;
    FillBlankScore fillBlankScore;
    BoolEnum isCaseSensitive;
    BoolEnum allowLookPaperAfterSubmit;
    BoolEnum allowCopyProblem;
    BoolEnum allowPasteAnswer;

    //时间配置
    LocalDateTime start;
    LocalDateTime end;
}
