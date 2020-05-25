package com.boot.mytt.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.mytt.core.entity.Employee;
import com.boot.mytt.core.pojo.EmployeePOJO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Component
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<EmployeePOJO> getAll();
}
