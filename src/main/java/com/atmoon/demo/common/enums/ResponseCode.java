package com.atmoon.demo.common.enums;
/**
 * <p>
 * 返回结果枚举
 * </p>
 *
 * @author zy
 * @since 2019-06-11
 */
public enum ResponseCode {
    /**
     * 操作成功
     */
    SUCCESS(0,"操作成功"),
    /**
     * 操作失败
     */
    ERROR(-1,"操作失败"),

    /**
     * 数据已存在
     */
    EXIST(ERROR.getCode(),"数据已存在"),

    /**
     * 数据为空
     */
    NOT_EXIST(ERROR.getCode(),"数据为空"),

    /**
     *
     * 查询成功
     */
    GET_SUCCESS(SUCCESS.getCode(),"查询成功"),

    /**
     * 查询失败
     */
    GET_FAIL(ERROR.getCode(),"查询失败"),
    /**
     * 参数为空
     */
    PARAM_NULL(ERROR.getCode(),"参数为空");


    /**
     * 编码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 设置返回信息
     * @param code 编码
     * @param msg 提示信息
     */
    ResponseCode(int code, String msg){
        this.code =code;
        this.msg = msg;
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
}
