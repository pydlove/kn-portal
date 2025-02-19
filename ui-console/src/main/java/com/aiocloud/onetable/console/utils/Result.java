package com.aiocloud.onetable.console.utils;

import java.io.Serializable;

/**
 * @auther ybin
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;//响应状态
    private String msg;//响应信息
    private Object data;//响应数据
    private long total;//总数

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public static Result success(String msg){
         return new Result(msg, SUCCESS_CODE);
    }

    public static Result success(String msg, Object obj){
        return new Result(msg, SUCCESS_CODE, obj);
    }

    public static Result success(String msg, Object obj, long total){
        return new Result(msg, SUCCESS_CODE, total, obj);
    }

    public static Result fail(String msg){
        return new Result(msg, FAIL_CODE);
    }

    public static Result fail(String msg, Object obj){
        return new Result(msg, FAIL_CODE, obj);
    }

    public static Result fail(String msg, Object obj, long total){
        return new Result(msg, FAIL_CODE, total, obj);
    }

    public Result() {
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public Result(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Result(String msg, int code, long total, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.total = total;
    }

    public void setObject(Object data) {
        this.data = data;
    }

    public void setObject(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public void setObject(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public void setObject(String msg, int code, long total, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
