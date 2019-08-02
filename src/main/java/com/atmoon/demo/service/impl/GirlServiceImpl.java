package com.atmoon.demo.service.impl;

import com.atmoon.demo.entity.Girl;
import com.atmoon.demo.mapper.GirlMapper;
import com.atmoon.demo.service.IGirlService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zy
 * @since 2019-07-19
 */
@Service
public class GirlServiceImpl extends ServiceImpl<GirlMapper, Girl> implements IGirlService {

    @Autowired
    GirlMapper girlMapper;

    @Override
    public Page<Girl> queryGirl(Page<Girl> page, String city) {
        return page.setRecords(girlMapper.queryGirl(page,city));
    }
}
