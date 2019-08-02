package com.atmoon.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: zy
 */
@Component
@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {

    /**
     * 文件上传使用类型
     */
    private String type;

    /**
     * 配置文件名
     */
    private String conf;

    /**
     * 获取文件实际路径
     */
    private String actualUrl;

}
