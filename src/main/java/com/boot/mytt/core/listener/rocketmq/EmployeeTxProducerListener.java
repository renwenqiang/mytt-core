package com.boot.mytt.core.listener.rocketmq;

import com.boot.mytt.core.listener.rocketmq.body.EmployeeBody;
import com.boot.mytt.core.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "employee_tx_producer")
public class EmployeeTxProducerListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        EmployeeBody body = JacksonUtils.json2pojo(new String((byte[]) message.getPayload()), EmployeeBody.class);
        log.info("body -> {}", body);
        if (true) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
