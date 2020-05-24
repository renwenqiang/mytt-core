package com.boot.mytt.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {
    private static final long serialVersionUID = -1420582785155907305L;

    private Long id;
    private String name;
    private Integer age;
    @TableId(type = IdType.ASSIGN_ID)
    private Long guid;
}
