package com.alpha.classpie.bcz.model;

import java.util.ArrayList;
import java.util.List;

public class TranslationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TranslationExample() {
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
