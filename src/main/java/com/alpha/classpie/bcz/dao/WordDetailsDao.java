package com.alpha.classpie.bcz.dao;





import com.alpha.classpie.bcz.model.WordDetails;


import java.util.List;

/**
 * 定义操作的接口
 */
public interface WordDetailsDao {
    List<WordDetails> getWordDetails(int count, int excludeId);
    List<WordDetails> getNotLearnWordDetails(int count);
    List<WordDetails> getNeedReviewWordDetails();
}
