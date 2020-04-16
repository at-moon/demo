package com.atmoon.demo.controller;


import com.atmoon.demo.entity.vo.TreeNode;
import com.atmoon.demo.service.ISysMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zy
 * @since 2020-04-16
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    ISysMenuService sysMenuService;

    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping("/tree")
    @ResponseBody
    public List<TreeNode> getMenuTree() {
        return sysMenuService.getMenuTree();
    }

    /**
     * 获取子菜单
     * 参数为空时获取根节点子菜单
     *
     * @param parentId
     * @return
     */
    @GetMapping("/getChildren")
    @ResponseBody
    public List<TreeNode> getChildren(String parentId) {
        return sysMenuService.getChildren(parentId);
    }

}

