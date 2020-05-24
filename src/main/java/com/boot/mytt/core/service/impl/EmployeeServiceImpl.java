package com.boot.mytt.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mytt.core.entity.Employee;
import com.boot.mytt.core.mapper.EmployeeMapper;
import com.boot.mytt.core.pojo.EmployeePOJO;
import com.boot.mytt.core.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeePOJO> getAll() {
        return employeeMapper.getAll();
    }
}
