package com.atmoon.demo.entity;

import lombok.Data;

/**
 * @Author: zy
 */
@Data
public class FdfsFile {

    /**
     * 文件类型（后缀）
     */
    private String fileType;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件内容
     */
    private byte[] content;

}
