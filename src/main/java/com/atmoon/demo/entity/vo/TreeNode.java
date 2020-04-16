package com.atmoon.demo.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * element-ui 树节点
 *
 * @author zy
 */
@Data
public class TreeNode {

    /**
     * 节点id
     */
    private String id;
    /**
     * 父节点id
     */
    private String parentId;
    /**
     * 节点标签
     */
    private String label;
    /**
     * 子树
     */
    private List<TreeNode> children;
    /**
     * 选择框是否禁用
     */
    private String disabled;
    /**
     * 是否为叶子节点
     * 仅在指定了 lazy 属性的情况下生效(叶子节点不渲染下拉按钮)
     */
    private String isLeaf;

}
