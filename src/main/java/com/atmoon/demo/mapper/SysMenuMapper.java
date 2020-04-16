package com.atmoon.demo.mapper;

import com.atmoon.demo.entity.SysMenu;
import com.atmoon.demo.entity.vo.TreeNode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2020-04-16
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取所有菜单
     *
     * @return
     */
    List<TreeNode> getAllMenu();

    /**
     * 获取子菜单
     *
     * @param parentId
     * @return
     */
    List<TreeNode> getChildren(@Param("parentId") String parentId);

}
