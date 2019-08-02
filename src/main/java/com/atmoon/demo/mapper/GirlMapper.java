package com.atmoon.demo.mapper;

import com.atmoon.demo.entity.Girl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2019-07-19
 */
public interface GirlMapper extends BaseMapper<Girl> {

    /**
     * @param page
     * @param city
     * @return
     */
    List<Girl> queryGirl(Page<Girl> page,@Param("city")String city);
}
