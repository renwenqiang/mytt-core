package com.boot.mytt.core.listener.rocketmq.body;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBody implements Serializable {
    private static final long serialVersionUID = -1608670986974107194L;

    private Long id;

    private String name;
}
