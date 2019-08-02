package com.atmoon.demo.service.impl;

import com.atmoon.demo.entity.Department;
import com.atmoon.demo.mapper.DepartmentMapper;
import com.atmoon.demo.service.IDepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
