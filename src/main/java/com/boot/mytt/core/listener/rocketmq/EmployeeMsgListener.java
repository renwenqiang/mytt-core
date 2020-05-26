package com.boot.mytt.core.listener.rocketmq;

import com.boot.mytt.core.listener.rocketmq.body.EmployeeBody;
import com.boot.mytt.core.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * @author renwq
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "employee_msg_topic", consumerGroup = "employee_msg_consumer")
public class EmployeeMsgListener implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            try {
                if (!CollectionUtils.isEmpty(msgs)) {
                    msgs.forEach(msg -> {
                        log.info("EmployeeMsgListener -> 【消费消息】 次数：{}, ext ：{}", msg.getReconsumeTimes(), msg);
                        EmployeeBody body = JacksonUtils.json2pojo(new String(msg.getBody()), EmployeeBody.class);
                        log.info("body -> {}", body);
                    });
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                log.error("消费消息失败", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
    }

    @Override
    public void onMessage(String message) {
        log.info("EmployeeMsgListener -> {}", "接收到消息");
    }
}
