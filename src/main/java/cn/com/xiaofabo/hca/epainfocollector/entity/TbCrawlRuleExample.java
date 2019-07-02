package cn.com.xiaofabo.hca.epainfocollector.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbCrawlRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCrawlRuleExample() {
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

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andMatchUrlIsNull() {
            addCriterion("match_url is null");
            return (Criteria) this;
        }

        public Criteria andMatchUrlIsNotNull() {
            addCriterion("match_url is not null");
            return (Criteria) this;
        }

        public Criteria andMatchUrlEqualTo(String value) {
            addCriterion("match_url =", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlNotEqualTo(String value) {
            addCriterion("match_url <>", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlGreaterThan(String value) {
            addCriterion("match_url >", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlGreaterThanOrEqualTo(String value) {
            addCriterion("match_url >=", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlLessThan(String value) {
            addCriterion("match_url <", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlLessThanOrEqualTo(String value) {
            addCriterion("match_url <=", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlLike(String value) {
            addCriterion("match_url like", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlNotLike(String value) {
            addCriterion("match_url not like", value, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlIn(List<String> values) {
            addCriterion("match_url in", values, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlNotIn(List<String> values) {
            addCriterion("match_url not in", values, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlBetween(String value1, String value2) {
            addCriterion("match_url between", value1, value2, "matchUrl");
            return (Criteria) this;
        }

        public Criteria andMatchUrlNotBetween(String value1, String value2) {
            addCriterion("match_url not between", value1, value2, "matchUrl");
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

        public Criteria andHrefIsNull() {
            addCriterion("href is null");
            return (Criteria) this;
        }

        public Criteria andHrefIsNotNull() {
            addCriterion("href is not null");
            return (Criteria) this;
        }

        public Criteria andHrefEqualTo(String value) {
            addCriterion("href =", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotEqualTo(String value) {
            addCriterion("href <>", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThan(String value) {
            addCriterion("href >", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThanOrEqualTo(String value) {
            addCriterion("href >=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThan(String value) {
            addCriterion("href <", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThanOrEqualTo(String value) {
            addCriterion("href <=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLike(String value) {
            addCriterion("href like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotLike(String value) {
            addCriterion("href not like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefIn(List<String> values) {
            addCriterion("href in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotIn(List<String> values) {
            addCriterion("href not in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefBetween(String value1, String value2) {
            addCriterion("href between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotBetween(String value1, String value2) {
            addCriterion("href not between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andCurrentPageIsNull() {
            addCriterion("current_page is null");
            return (Criteria) this;
        }

        public Criteria andCurrentPageIsNotNull() {
            addCriterion("current_page is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentPageEqualTo(String value) {
            addCriterion("current_page =", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageNotEqualTo(String value) {
            addCriterion("current_page <>", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageGreaterThan(String value) {
            addCriterion("current_page >", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageGreaterThanOrEqualTo(String value) {
            addCriterion("current_page >=", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageLessThan(String value) {
            addCriterion("current_page <", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageLessThanOrEqualTo(String value) {
            addCriterion("current_page <=", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageLike(String value) {
            addCriterion("current_page like", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageNotLike(String value) {
            addCriterion("current_page not like", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageIn(List<String> values) {
            addCriterion("current_page in", values, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageNotIn(List<String> values) {
            addCriterion("current_page not in", values, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageBetween(String value1, String value2) {
            addCriterion("current_page between", value1, value2, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageNotBetween(String value1, String value2) {
            addCriterion("current_page not between", value1, value2, "currentPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageIsNull() {
            addCriterion("total_page is null");
            return (Criteria) this;
        }

        public Criteria andTotalPageIsNotNull() {
            addCriterion("total_page is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPageEqualTo(String value) {
            addCriterion("total_page =", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotEqualTo(String value) {
            addCriterion("total_page <>", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageGreaterThan(String value) {
            addCriterion("total_page >", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageGreaterThanOrEqualTo(String value) {
            addCriterion("total_page >=", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageLessThan(String value) {
            addCriterion("total_page <", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageLessThanOrEqualTo(String value) {
            addCriterion("total_page <=", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageLike(String value) {
            addCriterion("total_page like", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotLike(String value) {
            addCriterion("total_page not like", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageIn(List<String> values) {
            addCriterion("total_page in", values, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotIn(List<String> values) {
            addCriterion("total_page not in", values, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageBetween(String value1, String value2) {
            addCriterion("total_page between", value1, value2, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotBetween(String value1, String value2) {
            addCriterion("total_page not between", value1, value2, "totalPage");
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

        public Criteria andPostTimeIsNull() {
            addCriterion("post_time is null");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNotNull() {
            addCriterion("post_time is not null");
            return (Criteria) this;
        }

        public Criteria andPostTimeEqualTo(String value) {
            addCriterion("post_time =", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotEqualTo(String value) {
            addCriterion("post_time <>", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThan(String value) {
            addCriterion("post_time >", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThanOrEqualTo(String value) {
            addCriterion("post_time >=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThan(String value) {
            addCriterion("post_time <", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThanOrEqualTo(String value) {
            addCriterion("post_time <=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLike(String value) {
            addCriterion("post_time like", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotLike(String value) {
            addCriterion("post_time not like", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeIn(List<String> values) {
            addCriterion("post_time in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotIn(List<String> values) {
            addCriterion("post_time not in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeBetween(String value1, String value2) {
            addCriterion("post_time between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotBetween(String value1, String value2) {
            addCriterion("post_time not between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andBodyContentIsNull() {
            addCriterion("body_content is null");
            return (Criteria) this;
        }

        public Criteria andBodyContentIsNotNull() {
            addCriterion("body_content is not null");
            return (Criteria) this;
        }

        public Criteria andBodyContentEqualTo(String value) {
            addCriterion("body_content =", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentNotEqualTo(String value) {
            addCriterion("body_content <>", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentGreaterThan(String value) {
            addCriterion("body_content >", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentGreaterThanOrEqualTo(String value) {
            addCriterion("body_content >=", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentLessThan(String value) {
            addCriterion("body_content <", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentLessThanOrEqualTo(String value) {
            addCriterion("body_content <=", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentLike(String value) {
            addCriterion("body_content like", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentNotLike(String value) {
            addCriterion("body_content not like", value, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentIn(List<String> values) {
            addCriterion("body_content in", values, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentNotIn(List<String> values) {
            addCriterion("body_content not in", values, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentBetween(String value1, String value2) {
            addCriterion("body_content between", value1, value2, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyContentNotBetween(String value1, String value2) {
            addCriterion("body_content not between", value1, value2, "bodyContent");
            return (Criteria) this;
        }

        public Criteria andBodyFileIsNull() {
            addCriterion("body_file is null");
            return (Criteria) this;
        }

        public Criteria andBodyFileIsNotNull() {
            addCriterion("body_file is not null");
            return (Criteria) this;
        }

        public Criteria andBodyFileEqualTo(String value) {
            addCriterion("body_file =", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileNotEqualTo(String value) {
            addCriterion("body_file <>", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileGreaterThan(String value) {
            addCriterion("body_file >", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileGreaterThanOrEqualTo(String value) {
            addCriterion("body_file >=", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileLessThan(String value) {
            addCriterion("body_file <", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileLessThanOrEqualTo(String value) {
            addCriterion("body_file <=", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileLike(String value) {
            addCriterion("body_file like", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileNotLike(String value) {
            addCriterion("body_file not like", value, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileIn(List<String> values) {
            addCriterion("body_file in", values, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileNotIn(List<String> values) {
            addCriterion("body_file not in", values, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileBetween(String value1, String value2) {
            addCriterion("body_file between", value1, value2, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andBodyFileNotBetween(String value1, String value2) {
            addCriterion("body_file not between", value1, value2, "bodyFile");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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