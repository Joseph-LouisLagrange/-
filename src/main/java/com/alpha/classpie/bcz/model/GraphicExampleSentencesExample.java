package com.alpha.classpie.bcz.model;

import java.util.ArrayList;
import java.util.List;

public class GraphicExampleSentencesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GraphicExampleSentencesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesIsNull() {
            addCriterion("example_sentences is null");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesIsNotNull() {
            addCriterion("example_sentences is not null");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesEqualTo(String value) {
            addCriterion("example_sentences =", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesNotEqualTo(String value) {
            addCriterion("example_sentences <>", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesGreaterThan(String value) {
            addCriterion("example_sentences >", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesGreaterThanOrEqualTo(String value) {
            addCriterion("example_sentences >=", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesLessThan(String value) {
            addCriterion("example_sentences <", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesLessThanOrEqualTo(String value) {
            addCriterion("example_sentences <=", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesLike(String value) {
            addCriterion("example_sentences like", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesNotLike(String value) {
            addCriterion("example_sentences not like", value, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesIn(List<String> values) {
            addCriterion("example_sentences in", values, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesNotIn(List<String> values) {
            addCriterion("example_sentences not in", values, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesBetween(String value1, String value2) {
            addCriterion("example_sentences between", value1, value2, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andExampleSentencesNotBetween(String value1, String value2) {
            addCriterion("example_sentences not between", value1, value2, "exampleSentences");
            return (Criteria) this;
        }

        public Criteria andTranslationIsNull() {
            addCriterion("translation is null");
            return (Criteria) this;
        }

        public Criteria andTranslationIsNotNull() {
            addCriterion("translation is not null");
            return (Criteria) this;
        }

        public Criteria andTranslationEqualTo(String value) {
            addCriterion("translation =", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotEqualTo(String value) {
            addCriterion("translation <>", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationGreaterThan(String value) {
            addCriterion("translation >", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationGreaterThanOrEqualTo(String value) {
            addCriterion("translation >=", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationLessThan(String value) {
            addCriterion("translation <", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationLessThanOrEqualTo(String value) {
            addCriterion("translation <=", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationLike(String value) {
            addCriterion("translation like", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotLike(String value) {
            addCriterion("translation not like", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationIn(List<String> values) {
            addCriterion("translation in", values, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotIn(List<String> values) {
            addCriterion("translation not in", values, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationBetween(String value1, String value2) {
            addCriterion("translation between", value1, value2, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotBetween(String value1, String value2) {
            addCriterion("translation not between", value1, value2, "translation");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNull() {
            addCriterion("image_path is null");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNotNull() {
            addCriterion("image_path is not null");
            return (Criteria) this;
        }

        public Criteria andImagePathEqualTo(String value) {
            addCriterion("image_path =", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotEqualTo(String value) {
            addCriterion("image_path <>", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThan(String value) {
            addCriterion("image_path >", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThanOrEqualTo(String value) {
            addCriterion("image_path >=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThan(String value) {
            addCriterion("image_path <", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThanOrEqualTo(String value) {
            addCriterion("image_path <=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLike(String value) {
            addCriterion("image_path like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotLike(String value) {
            addCriterion("image_path not like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathIn(List<String> values) {
            addCriterion("image_path in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotIn(List<String> values) {
            addCriterion("image_path not in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathBetween(String value1, String value2) {
            addCriterion("image_path between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotBetween(String value1, String value2) {
            addCriterion("image_path not between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNull() {
            addCriterion("key_word is null");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNotNull() {
            addCriterion("key_word is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWordEqualTo(String value) {
            addCriterion("key_word =", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotEqualTo(String value) {
            addCriterion("key_word <>", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThan(String value) {
            addCriterion("key_word >", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThanOrEqualTo(String value) {
            addCriterion("key_word >=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThan(String value) {
            addCriterion("key_word <", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThanOrEqualTo(String value) {
            addCriterion("key_word <=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLike(String value) {
            addCriterion("key_word like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotLike(String value) {
            addCriterion("key_word not like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordIn(List<String> values) {
            addCriterion("key_word in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotIn(List<String> values) {
            addCriterion("key_word not in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordBetween(String value1, String value2) {
            addCriterion("key_word between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotBetween(String value1, String value2) {
            addCriterion("key_word not between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdIsNull() {
            addCriterion("word_details_id is null");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdIsNotNull() {
            addCriterion("word_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdEqualTo(Integer value) {
            addCriterion("word_details_id =", value, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdNotEqualTo(Integer value) {
            addCriterion("word_details_id <>", value, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdGreaterThan(Integer value) {
            addCriterion("word_details_id >", value, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("word_details_id >=", value, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdLessThan(Integer value) {
            addCriterion("word_details_id <", value, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdLessThanOrEqualTo(Integer value) {
            addCriterion("word_details_id <=", value, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdIn(List<Integer> values) {
            addCriterion("word_details_id in", values, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdNotIn(List<Integer> values) {
            addCriterion("word_details_id not in", values, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdBetween(Integer value1, Integer value2) {
            addCriterion("word_details_id between", value1, value2, "wordDetailsId");
            return (Criteria) this;
        }

        public Criteria andWordDetailsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("word_details_id not between", value1, value2, "wordDetailsId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
