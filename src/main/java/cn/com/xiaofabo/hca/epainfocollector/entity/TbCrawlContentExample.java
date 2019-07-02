package cn.com.xiaofabo.hca.epainfocollector.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbCrawlContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCrawlContentExample() {
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

        public Criteria andStartUrlIsNull() {
            addCriterion("start_url is null");
            return (Criteria) this;
        }

        public Criteria andStartUrlIsNotNull() {
            addCriterion("start_url is not null");
            return (Criteria) this;
        }

        public Criteria andStartUrlEqualTo(String value) {
            addCriterion("start_url =", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotEqualTo(String value) {
            addCriterion("start_url <>", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlGreaterThan(String value) {
            addCriterion("start_url >", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlGreaterThanOrEqualTo(String value) {
            addCriterion("start_url >=", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlLessThan(String value) {
            addCriterion("start_url <", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlLessThanOrEqualTo(String value) {
            addCriterion("start_url <=", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlLike(String value) {
            addCriterion("start_url like", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotLike(String value) {
            addCriterion("start_url not like", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlIn(List<String> values) {
            addCriterion("start_url in", values, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotIn(List<String> values) {
            addCriterion("start_url not in", values, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlBetween(String value1, String value2) {
            addCriterion("start_url between", value1, value2, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotBetween(String value1, String value2) {
            addCriterion("start_url not between", value1, value2, "startUrl");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameIsNull() {
            addCriterion("body_file_name is null");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameIsNotNull() {
            addCriterion("body_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameEqualTo(String value) {
            addCriterion("body_file_name =", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameNotEqualTo(String value) {
            addCriterion("body_file_name <>", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameGreaterThan(String value) {
            addCriterion("body_file_name >", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("body_file_name >=", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameLessThan(String value) {
            addCriterion("body_file_name <", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameLessThanOrEqualTo(String value) {
            addCriterion("body_file_name <=", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameLike(String value) {
            addCriterion("body_file_name like", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameNotLike(String value) {
            addCriterion("body_file_name not like", value, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameIn(List<String> values) {
            addCriterion("body_file_name in", values, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameNotIn(List<String> values) {
            addCriterion("body_file_name not in", values, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameBetween(String value1, String value2) {
            addCriterion("body_file_name between", value1, value2, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileNameNotBetween(String value1, String value2) {
            addCriterion("body_file_name not between", value1, value2, "bodyFileName");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlIsNull() {
            addCriterion("body_file_url is null");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlIsNotNull() {
            addCriterion("body_file_url is not null");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlEqualTo(String value) {
            addCriterion("body_file_url =", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlNotEqualTo(String value) {
            addCriterion("body_file_url <>", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlGreaterThan(String value) {
            addCriterion("body_file_url >", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("body_file_url >=", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlLessThan(String value) {
            addCriterion("body_file_url <", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlLessThanOrEqualTo(String value) {
            addCriterion("body_file_url <=", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlLike(String value) {
            addCriterion("body_file_url like", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlNotLike(String value) {
            addCriterion("body_file_url not like", value, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlIn(List<String> values) {
            addCriterion("body_file_url in", values, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlNotIn(List<String> values) {
            addCriterion("body_file_url not in", values, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlBetween(String value1, String value2) {
            addCriterion("body_file_url between", value1, value2, "bodyFileUrl");
            return (Criteria) this;
        }

        public Criteria andBodyFileUrlNotBetween(String value1, String value2) {
            addCriterion("body_file_url not between", value1, value2, "bodyFileUrl");
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