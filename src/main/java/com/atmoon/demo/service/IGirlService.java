package com.atmoon.demo.service;

import com.atmoon.demo.entity.Girl;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zy
 * @since 2019-07-19
 */
public interface IGirlService extends IService<Girl> {

    /**
     * 分页带参获取列表
     * @param page
     * @param city
     * @return
     */
    Page<Girl> queryGirl(Page<Girl> page,String city);

}
