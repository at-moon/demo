package com.atmoon.demo.common.api;


import com.atmoon.demo.common.enums.ResponseCode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 表格vo
 * </p>
 *
 * @author zy
 * @since 2019-06-11
 */
@Data
public class TableVo<T> implements Serializable {

    private static final long serialVersionUID = -8922389536127247174L;
    private int code;
    private String msg;
    private int count;
    private List<T> data;

    private TableVo() {}

    private TableVo(int count, List<T> data) {
        this.count = count;
        this.data = data;
    }

    private TableVo(int code, String msg, int count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    /**
     * 返回成功
     * 根据总数和列表信息
     * @param count
     * @param data
     * @param <T>
     * @return
     */
    public static <T> TableVo<T> createSuccessTable(int count, List<T> data) {
        return new TableVo(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count,data);
    }

    /**
     * 创建table
     * @param code
     * @param msg
     * @param count
     * @param data
     * @param <T>
     * @return
     */
    public static <T> TableVo<T> createTable(int code, String msg, int count, List<T> data) {
        return new TableVo(code,msg,count,data);
    }
}
