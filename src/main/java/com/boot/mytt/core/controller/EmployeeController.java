package com.boot.mytt.core.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.mytt.core.entity.Employee;
import com.boot.mytt.core.pojo.EmployeePOJO;
import com.boot.mytt.core.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author renwq
 * @date 2020/5/25
 */
@RestController
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("getAll")
    public List getAll() {
        List<EmployeePOJO> list = employeeService.getAll();
        logger.info("getAll: ", list);
        return list;
    }

    @RequestMapping("getPart")
    public List getPart() {
        List<Employee> list = employeeService.list(Wrappers.<Employee>lambdaQuery()
                .select(Employee::getId, Employee::getName));
        logger.info("getAll: ", list);
        return list;
    }

    @RequestMapping("getOne")
    public String getOne(Long id) {
        logger.info("getOne: ", employeeService.getById(id));
        return "OK";
    }

    @RequestMapping("insert")
    public String insert() {
        for (int k = 0; k < 2; k++)
            employeeService.save(new Employee(null, "zhang", 22, null));
        return "OK";
    }
}
