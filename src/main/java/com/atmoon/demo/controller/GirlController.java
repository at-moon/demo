package com.atmoon.demo.controller;


import com.atmoon.demo.common.api.TableVo;
import com.atmoon.demo.entity.Girl;
import com.atmoon.demo.service.IGirlService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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

}

