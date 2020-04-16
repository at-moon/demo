package com.atmoon.demo.service.impl;

import com.atmoon.demo.entity.SysMenu;
import com.atmoon.demo.entity.vo.TreeNode;
import com.atmoon.demo.mapper.SysMenuMapper;
import com.atmoon.demo.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.atmoon.demo.constant.DemoConstant.ROOT_NODE_ID;
import static com.atmoon.demo.constant.DemoConstant.ROOT_NODE_NAME;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-16
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    SysMenuMapper sysMenuDAO;

    @Override
    public List<TreeNode> getMenuTree() {
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode root = getRootNode();
        Map<String, List<TreeNode>> map = getAllMenuMap();
        root.setChildren(getChildren(root.getId(), map));
        treeNodes.add(root);
        return treeNodes;
    }

    @Override
    public List<TreeNode> getChildren(String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            parentId = ROOT_NODE_ID;
        }
        return sysMenuDAO.getChildren(parentId);
    }

    /**
     * 根据id获取子节点
     *
     * @param parentId
     * @param map
     * @return
     */
    private List<TreeNode> getChildren(String parentId, Map<String, List<TreeNode>> map) {
        List<TreeNode> children = map.get(parentId);
        if (children == null) {
            return null;
        }
        for (TreeNode node : children) {
            node.setChildren(getChildren(node.getId(), map));
        }
        return children;
    }

    /**
     * 获取所有父节点id对应子节点集合
     *
     * @return
     */
    private Map<String, List<TreeNode>> getAllMenuMap() {
        Map<String, List<TreeNode>> map = new HashMap<>(8);
        List<TreeNode> temp;
        List<TreeNode> menuList = sysMenuDAO.getAllMenu();
        for (TreeNode node : menuList) {
            String parentId = node.getParentId();
            if (map.containsKey(parentId)) {
                map.get(parentId).add(node);
            } else {
                temp = new ArrayList<>();
                temp.add(node);
                map.put(parentId, temp);
            }
        }
        return map;
    }

    /**
     * 获取根节点
     *
     * @return
     */
    private TreeNode getRootNode() {
        TreeNode root = new TreeNode();
        root.setId(ROOT_NODE_ID);
        root.setLabel(ROOT_NODE_NAME);
        return root;
    }
}
