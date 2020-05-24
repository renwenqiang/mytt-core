package com.boot.mytt.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.mytt.core.entity.Employee;
import com.boot.mytt.core.pojo.EmployeePOJO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<EmployeePOJO> getAll();
}
