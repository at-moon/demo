package com.atmoon.demo.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出工具类
 *
 * @Author: zy
 */
public class ExportUtil {

    /**
     * 转换表单数据
     *
     * @param formData 表单数据
     * @return map
     */
    private static Map<String, String> transFormData(Map<String, Object> formData) {
        Map<String, String> map = new HashMap<>(16);
        if (formData == null) {
            return map;
        }
        // 控件编码对应结构体
        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            if (entry.getValue() instanceof String) {
                map.put(entry.getKey(), entry.getValue().toString());
            } else if (entry.getValue() instanceof Map) {
                Map<String, String> fieldData = (Map<String, String>) entry.getValue();
                // 控件编码.属性：属性值
                for (Map.Entry<String, String> field : fieldData.entrySet()) {
                    map.put(entry.getKey() + "." + field.getKey(), field.getValue());
                }
            }
        }
        return map;
    }

    /**
     * 根据传入表单数据对模板进行替换
     *
     * @param formData 表单数据
     * @return 输入流
     * @throws Exception 异常
     */
    public static InputStream transTemplateByPoi(Map<String, Object> formData) throws Exception {
        Map<String, String> map = transFormData(formData);
        // 获取模板
        String templatePath = "F:\\template.doc";
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //替换数据
        for (Map.Entry<String, String> entry : map.entrySet()) {
            range.replaceText("${" + entry.getKey() + "}", entry.getValue() == null ? "" : entry.getValue());
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        doc.write(b);
        InputStream inputStream = new ByteArrayInputStream(b.toByteArray());
        is.close();
        return inputStream;
    }


    /**
     * 导出文件
     *
     * @param is       文件输入流
     * @param response 响应
     * @throws IOException 输入输出
     */
    public static void export(InputStream is, HttpServletResponse response) throws IOException {
        // 导出文件名,实际使用可作为参数传入
        String exportFileName = "信息登记表.doc";
        ServletOutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(exportFileName, "UTF-8"))));
            out = response.getOutputStream();
            // 缓冲区
            byte[] buffer = new byte[512];
            int bytesToRead;
            while ((bytesToRead = is.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("按照模板生成文件报错", ex);
        } finally {
            if (is != null) {
                is.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * pdf在线预览
     *
     * @param pdfStream 输入文件流
     * @param response  响应
     * @throws Exception 异常
     */
    public static void pdfOnlinePreview(InputStream pdfStream, HttpServletResponse response) throws Exception {
        String fileName = "信息登记表.pdf";
        ServletOutputStream out;
        response.setCharacterEncoding("utf-8");
        //设置类型及文件名
        response.setHeader("Content-Disposition", "inline;filename="
                .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
        response.setContentType("application/pdf");
        // 缓冲区
        byte[] buffer = new byte[512];
        out = response.getOutputStream();
        int bytesToRead;
        while ((bytesToRead = pdfStream.read(buffer)) != -1) {
            out.write(buffer, 0, bytesToRead);
        }
        out.close();
    }


}
