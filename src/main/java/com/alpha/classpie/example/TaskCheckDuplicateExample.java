package com.alpha.classpie.example;

import java.util.ArrayList;
import java.util.List;

public class TaskCheckDuplicateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskCheckDuplicateExample() {
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

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andWarningValueIsNull() {
            addCriterion("warning_value is null");
            return (Criteria) this;
        }

        public Criteria andWarningValueIsNotNull() {
            addCriterion("warning_value is not null");
            return (Criteria) this;
        }

        public Criteria andWarningValueEqualTo(Integer value) {
            addCriterion("warning_value =", value, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueNotEqualTo(Integer value) {
            addCriterion("warning_value <>", value, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueGreaterThan(Integer value) {
            addCriterion("warning_value >", value, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("warning_value >=", value, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueLessThan(Integer value) {
            addCriterion("warning_value <", value, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueLessThanOrEqualTo(Integer value) {
            addCriterion("warning_value <=", value, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueIn(List<Integer> values) {
            addCriterion("warning_value in", values, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueNotIn(List<Integer> values) {
            addCriterion("warning_value not in", values, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueBetween(Integer value1, Integer value2) {
            addCriterion("warning_value between", value1, value2, "warningValue");
            return (Criteria) this;
        }

        public Criteria andWarningValueNotBetween(Integer value1, Integer value2) {
            addCriterion("warning_value not between", value1, value2, "warningValue");
            return (Criteria) this;
        }

        public Criteria andIsReturnIsNull() {
            addCriterion("is_return is null");
            return (Criteria) this;
        }

        public Criteria andIsReturnIsNotNull() {
            addCriterion("is_return is not null");
            return (Criteria) this;
        }

        public Criteria andIsReturnEqualTo(String value) {
            addCriterion("is_return =", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotEqualTo(String value) {
            addCriterion("is_return <>", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnGreaterThan(String value) {
            addCriterion("is_return >", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnGreaterThanOrEqualTo(String value) {
            addCriterion("is_return >=", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnLessThan(String value) {
            addCriterion("is_return <", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnLessThanOrEqualTo(String value) {
            addCriterion("is_return <=", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnLike(String value) {
            addCriterion("is_return like", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotLike(String value) {
            addCriterion("is_return not like", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnIn(List<String> values) {
            addCriterion("is_return in", values, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotIn(List<String> values) {
            addCriterion("is_return not in", values, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnBetween(String value1, String value2) {
            addCriterion("is_return between", value1, value2, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotBetween(String value1, String value2) {
            addCriterion("is_return not between", value1, value2, "isReturn");
            return (Criteria) this;
        }

        public Criteria andReturnValueIsNull() {
            addCriterion("return_value is null");
            return (Criteria) this;
        }

        public Criteria andReturnValueIsNotNull() {
            addCriterion("return_value is not null");
            return (Criteria) this;
        }

        public Criteria andReturnValueEqualTo(Integer value) {
            addCriterion("return_value =", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueNotEqualTo(Integer value) {
            addCriterion("return_value <>", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueGreaterThan(Integer value) {
            addCriterion("return_value >", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_value >=", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueLessThan(Integer value) {
            addCriterion("return_value <", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueLessThanOrEqualTo(Integer value) {
            addCriterion("return_value <=", value, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueIn(List<Integer> values) {
            addCriterion("return_value in", values, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueNotIn(List<Integer> values) {
            addCriterion("return_value not in", values, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueBetween(Integer value1, Integer value2) {
            addCriterion("return_value between", value1, value2, "returnValue");
            return (Criteria) this;
        }

        public Criteria andReturnValueNotBetween(Integer value1, Integer value2) {
            addCriterion("return_value not between", value1, value2, "returnValue");
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
