package com.atmoon.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.atmoon.demo.common.api.TableVo;
import com.atmoon.demo.entity.FdfsFile;
import com.atmoon.demo.entity.Girl;
import com.atmoon.demo.service.IGirlService;
import com.atmoon.demo.util.ExportUtil;
import com.atmoon.demo.util.FastDfsUtil;
import com.atmoon.demo.util.FileFormatUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zy
 * @since 2019-07-19
 */
@RestController
@RequestMapping("/girl")
public class GirlController {


    @Resource
    private IGirlService girlService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello " + name;
    }

    @GetMapping("/list")
    @ResponseBody
    public TableVo<Girl> queryStaffByAge(Page<Girl> page) {
        page = girlService.queryGirl(page, null);
        return TableVo.createSuccessTable(page.getTotal(), page.getRecords());
    }

    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject singleFileUpload(MultipartFile file) throws Exception {
        JSONObject object = new JSONObject();
        FdfsFile fdfsFile = FastDfsUtil.saveFile(file);
        object.put("path", fdfsFile.getPath());
        object.put("type", fdfsFile.getFileType());
        object.put("name", fdfsFile.getName());
        return object;
    }

    /**
     * 下载文档
     *
     * @param data     替换数据
     * @param response 响应
     */
    @PostMapping("/downWord")
    public void downWord(String data, HttpServletResponse response) throws Exception {
        Map<String, Object> formData = JSONObject.parseObject(data);
        // 将表单数据加载到word模板中
        InputStream wordStream = ExportUtil.transTemplateByPoi(formData);
        // 下载
        ExportUtil.export(wordStream, response);
    }

    /**
     * 在线预览
     *
     * @param data     替换数据
     * @param response 响应
     */
    @PostMapping("/onlinePreview")
    public void onlinePreview(String data, HttpServletResponse response) throws Exception {
        Map<String, Object> formData = JSONObject.parseObject(data);
        // 将表单数据加载到word模板中
        InputStream wordStream = ExportUtil.transTemplateByPoi(formData);
        // 将生成的word转为pdf
        InputStream pdfStream = FileFormatUtil.wordToPdf(wordStream);
        // pdf在线预览
        ExportUtil.pdfOnlinePreview(pdfStream, response);
    }

}

