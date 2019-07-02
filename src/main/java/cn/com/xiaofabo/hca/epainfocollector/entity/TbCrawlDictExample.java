package cn.com.xiaofabo.hca.epainfocollector.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbCrawlDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCrawlDictExample() {
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

        public Criteria andDKeyIsNull() {
            addCriterion("d_key is null");
            return (Criteria) this;
        }

        public Criteria andDKeyIsNotNull() {
            addCriterion("d_key is not null");
            return (Criteria) this;
        }

        public Criteria andDKeyEqualTo(String value) {
            addCriterion("d_key =", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyNotEqualTo(String value) {
            addCriterion("d_key <>", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyGreaterThan(String value) {
            addCriterion("d_key >", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyGreaterThanOrEqualTo(String value) {
            addCriterion("d_key >=", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyLessThan(String value) {
            addCriterion("d_key <", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyLessThanOrEqualTo(String value) {
            addCriterion("d_key <=", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyLike(String value) {
            addCriterion("d_key like", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyNotLike(String value) {
            addCriterion("d_key not like", value, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyIn(List<String> values) {
            addCriterion("d_key in", values, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyNotIn(List<String> values) {
            addCriterion("d_key not in", values, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyBetween(String value1, String value2) {
            addCriterion("d_key between", value1, value2, "dKey");
            return (Criteria) this;
        }

        public Criteria andDKeyNotBetween(String value1, String value2) {
            addCriterion("d_key not between", value1, value2, "dKey");
            return (Criteria) this;
        }

        public Criteria andDValueIsNull() {
            addCriterion("d_value is null");
            return (Criteria) this;
        }

        public Criteria andDValueIsNotNull() {
            addCriterion("d_value is not null");
            return (Criteria) this;
        }

        public Criteria andDValueEqualTo(String value) {
            addCriterion("d_value =", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueNotEqualTo(String value) {
            addCriterion("d_value <>", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueGreaterThan(String value) {
            addCriterion("d_value >", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueGreaterThanOrEqualTo(String value) {
            addCriterion("d_value >=", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueLessThan(String value) {
            addCriterion("d_value <", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueLessThanOrEqualTo(String value) {
            addCriterion("d_value <=", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueLike(String value) {
            addCriterion("d_value like", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueNotLike(String value) {
            addCriterion("d_value not like", value, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueIn(List<String> values) {
            addCriterion("d_value in", values, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueNotIn(List<String> values) {
            addCriterion("d_value not in", values, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueBetween(String value1, String value2) {
            addCriterion("d_value between", value1, value2, "dValue");
            return (Criteria) this;
        }

        public Criteria andDValueNotBetween(String value1, String value2) {
            addCriterion("d_value not between", value1, value2, "dValue");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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