package com.boot.mytt.core.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.mytt.core.distributelock.aspect.RedisLock;
import com.boot.mytt.core.entity.Employee;
import com.boot.mytt.core.listener.rocketmq.body.EmployeeBody;
import com.boot.mytt.core.pojo.EmployeePOJO;
import com.boot.mytt.core.redis.util.JacksonRedisUtils;
import com.boot.mytt.core.service.EmployeeService;
import com.boot.mytt.core.util.JacksonUtils;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
public class EmployeeController {

    @Resource
    private JacksonRedisUtils jacksonRedisUtils;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 跳转首页 demo.html
    @RequestMapping("toDemo")
    public String toDemo() {
        return "demo.html";
    }

    // 单行注释
    // 获取所有
    @RequestMapping("getAll")
    public List getAll() {
        List<EmployeePOJO> list = employeeService.getAll();
        logger.info("getAll: ", list);
        return list;
    }

    /* 这也是注释 */
    // 获取部分
    @RequestMapping("getPart")
    public List getPart() {

        List<Employee> list = employeeService.list(Wrappers.<Employee>lambdaQuery()
                .select(Employee::getId, Employee::getName));
        logger.info("getAll: ", list);
        return list;
    }

    // 获取一个
    @RequestMapping("getOne")
    public String getOne(Long id) {
        logger.info("getOne: ", employeeService.getById(id));
        return "OK";
    }

    // 插入操作
    @RequestMapping("insert")
    public String insert() {
        for (int k = 0; k < 2; k++)
            employeeService.save(new Employee(null, "zhang", 22, null));
        return "OK";
    }

    // 验证操作
    @RequestMapping("checkValid")
    public String checkValid(@RequestBody @Valid EmployeePOJO employeePOJO) {
        logger.info("getOne: ", employeePOJO);
        return "OK";
    }

    // 验证缓存 增加
    @RequestMapping("cacheAadd")
    public String cacheAadd(@RequestBody @Valid EmployeePOJO employeePOJO) {
        logger.info("getOne: ", employeePOJO);
        jacksonRedisUtils.valueDelete("testCache");
        Map<String, String> map = new HashMap<>();
        map.put(employeePOJO.getName(), employeePOJO.getName());
        jacksonRedisUtils.hashPutAll("testCache", map);
        return "OK";
    }


    // 验证缓存 查看
    @RequestMapping("cacheSelect")
    public String cacheSelect(@RequestBody @Valid EmployeePOJO employeePOJO) {
        logger.info("getOne: ", employeePOJO);
        Map<String, String> map = jacksonRedisUtils.hashEntries("testCache");
        for(Map.Entry<String, String> entry : map.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey+":"+mapValue);
        }
        return "OK";
    }

    // 验证分布式锁
    @RequestMapping("validDistributeLock")
    @RedisLock(value = "validDistributeLock")
    public String validDistributeLock() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        return "OK";
    }

    // 验证事务消息
    @RequestMapping("txMessage")
    public String txMessage() {
        Message<String> msg = MessageBuilder.withPayload(JacksonUtils.obj2json(new EmployeeBody(2L, "txMsg"))).build();
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction("employee_tx_producer", "employee_tx_topic", msg, null);
        if (sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
            logger.info("txMsg 发送成功");
        } else {
            logger.info("txMsg 发送失败");
        }
        return "OK";
    }

    // 验证普通消息
    @RequestMapping("generalMessage")
    public String generalMessage() {
        this.rocketMQTemplate.syncSend("employee_msg_topic", JacksonUtils.obj2json(new EmployeeBody(3L, "generalMessage")));
        return "OK";
    }
}
