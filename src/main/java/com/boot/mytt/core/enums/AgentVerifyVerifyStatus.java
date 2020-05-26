package com.boot.mytt.core.enums;

import lombok.Getter;

@Getter
public enum AgentVerifyVerifyStatus {
    WAIT(1),
    SUCCESS(2),
    FAIL(2);

    private Integer verifyStatus;

    AgentVerifyVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public static void main(String[] args) {
        System.out.println(AgentVerifyVerifyStatus.SUCCESS);
    }
}
