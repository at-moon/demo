package com.atmoon.demo.service;

import com.atmoon.demo.entity.SysMenu;
import com.atmoon.demo.entity.vo.TreeNode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zy
 * @since 2020-04-16
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单树
     *
     * @return
     */
    List<TreeNode> getMenuTree();

    /**
     * 获取子菜单
     * 参数为空时获取根节点子菜单
     *
     * @param parentId
     * @return
     */
    List<TreeNode> getChildren(String parentId);

}
