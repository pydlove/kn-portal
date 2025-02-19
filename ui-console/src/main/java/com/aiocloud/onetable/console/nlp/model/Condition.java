package com.aiocloud.onetable.console.nlp.model;

/**
 * @auther ybin
 */
public class Condition {

    private String leftValue;
    private String operator;
    private String rightValue;
    private String relation = " and ";

    public Condition(){

    }

    public Condition(String leftValue, String operator, String rightValue) {
        this.leftValue = leftValue;
        this.operator = operator;
        this.rightValue = rightValue;
    }

    public String generateCondition(){
        return String.join("", this.leftValue, " ", this.operator, " ", this.rightValue);
    }

    public boolean fillComplated(){
        return leftValue != null && operator != null && rightValue != null;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
