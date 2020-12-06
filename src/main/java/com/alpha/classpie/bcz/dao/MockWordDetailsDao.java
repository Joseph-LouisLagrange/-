package com.alpha.classpie.bcz.dao;





import com.alpha.classpie.bcz.model.GraphicExampleSentences;
import com.alpha.classpie.bcz.model.WordDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试使用的wordDetailsDao,数据层
 */
public class MockWordDetailsDao implements WordDetailsDao {
    @Override
    public List<WordDetails> getWordDetails(int count, int excludeId) {
     return null;
    }

    @Override
    public List<WordDetails> getNotLearnWordDetails(int count) {
        return null;
    }

    @Override
    public List<WordDetails> getNeedReviewWordDetails() {
        return null;
    }
}
