package com.boot.mytt.core.controller;

import com.boot.mytt.core.entity.ClassInfo;
import com.boot.mytt.core.entity.Classes;
import com.boot.mytt.core.mapper.ClassMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试连表查询
 */
@RestController
@Slf4j
public class MybatisJoinController {

    @Resource
    private ClassMapper classMapper;

    @RequestMapping("getClasses")
    public String getClasses() {
        Classes c = classMapper.getClasses(1);
        log.info("getClasses: {}", c);
        return "OK";
    }

    @RequestMapping("getClasses2")
    public String getClasses2() {
        Classes c = classMapper.getClasses2(1);
        log.info("getClasses2: {}", c);
        return "OK";
    }

    @RequestMapping("getClasses3")
    public String getClasses3() {
        ClassInfo c = classMapper.getClass3(1);
        log.info("getClasses3: {}", c);
        return "OK";
    }

    @RequestMapping("getClasses4")
    public String getClasses4() {
        ClassInfo c = classMapper.getClass4(1);
        log.info("getClasses4: {}", c);
        return "OK";
    }
}
