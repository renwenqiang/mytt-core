package com.boot.mytt.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.mytt.core.entity.Employee;
import com.boot.mytt.core.pojo.EmployeePOJO;

import java.util.List;

/**
 * @author renwq
 * @date 2020/5/25
 */
public interface EmployeeService extends IService<Employee> {

    List<EmployeePOJO> getAll();
}
