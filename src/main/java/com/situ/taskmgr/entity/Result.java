package com.situ.taskmgr.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Result {

    /** 
     * 状态码
     */
    private Integer code;

    /** 
     * 消息
     */
    private String msg;

    /** 
     * 数据
     */
    private Object data;
    
    //private Integer count = 100;

    /** 
     */
    public static Result success() {
        return success(0, null, null);
    }

    /** 
     */
    public static Result success( int code) {
        return success(code, null, null);
    }

    /** 
     */
    public static Result success( String msg) {
        return success(0, msg, null);
    }

    /** 
     */
    public static Result success( Object data) {
        return success(0, null, data);
    }

    /** 
     */
    public static Result success( int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /** 
     */
    public static Result error() {
        return error(-1, "服务器发生错误, 请求联系管理员");
    }

    /** 
     */
    public static Result error( String msg) {
        return error(-1, msg);
    }

    /**
     */
    public static Result error( Integer code) {
        return error(code, null);
    }
    /** 
     */
    public static Result error( int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
