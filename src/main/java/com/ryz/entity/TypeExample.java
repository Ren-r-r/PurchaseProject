package com.ryz.entity;

import java.util.ArrayList;
import java.util.List;

public class TypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TypeExample() {
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

        public Criteria andCIdIsNull() {
            addCriterion("C_ID is null");
            return (Criteria) this;
        }

        public Criteria andCIdIsNotNull() {
            addCriterion("C_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCIdEqualTo(Long value) {
            addCriterion("C_ID =", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotEqualTo(Long value) {
            addCriterion("C_ID <>", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdGreaterThan(Long value) {
            addCriterion("C_ID >", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdGreaterThanOrEqualTo(Long value) {
            addCriterion("C_ID >=", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdLessThan(Long value) {
            addCriterion("C_ID <", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdLessThanOrEqualTo(Long value) {
            addCriterion("C_ID <=", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdIn(List<Long> values) {
            addCriterion("C_ID in", values, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotIn(List<Long> values) {
            addCriterion("C_ID not in", values, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdBetween(Long value1, Long value2) {
            addCriterion("C_ID between", value1, value2, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotBetween(Long value1, Long value2) {
            addCriterion("C_ID not between", value1, value2, "cId");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeIsNull() {
            addCriterion("C_NUM_MATERIAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeIsNotNull() {
            addCriterion("C_NUM_MATERIAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeEqualTo(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE =", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeNotEqualTo(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE <>", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeGreaterThan(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE >", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeGreaterThanOrEqualTo(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE >=", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeLessThan(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE <", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeLessThanOrEqualTo(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE <=", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeLike(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE like", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeNotLike(String value) {
            addCriterion("C_NUM_MATERIAL_TYPE not like", value, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeIn(List<String> values) {
            addCriterion("C_NUM_MATERIAL_TYPE in", values, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeNotIn(List<String> values) {
            addCriterion("C_NUM_MATERIAL_TYPE not in", values, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeBetween(String value1, String value2) {
            addCriterion("C_NUM_MATERIAL_TYPE between", value1, value2, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNumMaterialTypeNotBetween(String value1, String value2) {
            addCriterion("C_NUM_MATERIAL_TYPE not between", value1, value2, "cNumMaterialType");
            return (Criteria) this;
        }

        public Criteria andCNameIsNull() {
            addCriterion("C_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCNameIsNotNull() {
            addCriterion("C_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCNameEqualTo(String value) {
            addCriterion("C_NAME =", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotEqualTo(String value) {
            addCriterion("C_NAME <>", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameGreaterThan(String value) {
            addCriterion("C_NAME >", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameGreaterThanOrEqualTo(String value) {
            addCriterion("C_NAME >=", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLessThan(String value) {
            addCriterion("C_NAME <", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLessThanOrEqualTo(String value) {
            addCriterion("C_NAME <=", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLike(String value) {
            addCriterion("C_NAME like", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotLike(String value) {
            addCriterion("C_NAME not like", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameIn(List<String> values) {
            addCriterion("C_NAME in", values, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotIn(List<String> values) {
            addCriterion("C_NAME not in", values, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameBetween(String value1, String value2) {
            addCriterion("C_NAME between", value1, value2, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotBetween(String value1, String value2) {
            addCriterion("C_NAME not between", value1, value2, "cName");
            return (Criteria) this;
        }

        public Criteria andCTypeDecIsNull() {
            addCriterion("C_TYPE_DEC is null");
            return (Criteria) this;
        }

        public Criteria andCTypeDecIsNotNull() {
            addCriterion("C_TYPE_DEC is not null");
            return (Criteria) this;
        }

        public Criteria andCTypeDecEqualTo(String value) {
            addCriterion("C_TYPE_DEC =", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecNotEqualTo(String value) {
            addCriterion("C_TYPE_DEC <>", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecGreaterThan(String value) {
            addCriterion("C_TYPE_DEC >", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecGreaterThanOrEqualTo(String value) {
            addCriterion("C_TYPE_DEC >=", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecLessThan(String value) {
            addCriterion("C_TYPE_DEC <", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecLessThanOrEqualTo(String value) {
            addCriterion("C_TYPE_DEC <=", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecLike(String value) {
            addCriterion("C_TYPE_DEC like", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecNotLike(String value) {
            addCriterion("C_TYPE_DEC not like", value, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecIn(List<String> values) {
            addCriterion("C_TYPE_DEC in", values, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecNotIn(List<String> values) {
            addCriterion("C_TYPE_DEC not in", values, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecBetween(String value1, String value2) {
            addCriterion("C_TYPE_DEC between", value1, value2, "cTypeDec");
            return (Criteria) this;
        }

        public Criteria andCTypeDecNotBetween(String value1, String value2) {
            addCriterion("C_TYPE_DEC not between", value1, value2, "cTypeDec");
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