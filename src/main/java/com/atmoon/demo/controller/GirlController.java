package com.atmoon.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.atmoon.demo.common.api.TableVo;
import com.atmoon.demo.entity.FdfsFile;
import com.atmoon.demo.entity.Girl;
import com.atmoon.demo.service.IGirlService;
import com.atmoon.demo.util.FastDfsUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zy
 * @since 2019-07-19
 */
@Controller
@RequestMapping("/girl")
public class GirlController {


    @Autowired
    private IGirlService girlService;

    @GetMapping("/list")
    @ResponseBody
    public TableVo<Girl> queryStaffByAge(Page<Girl> page) {
        page = girlService.queryGirl(page,null);
        return TableVo.createSuccessTable(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject singleFileUpload(MultipartFile file) throws Exception{
        JSONObject object = new JSONObject();
        FdfsFile fdfsFile = FastDfsUtil.saveFile(file);
        object.put("path",fdfsFile.getPath());
        object.put("type",fdfsFile.getFileType());
        object.put("name",fdfsFile.getName());
        return object;
    }

}

