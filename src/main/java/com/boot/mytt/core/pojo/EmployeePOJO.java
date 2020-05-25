package com.boot.mytt.core.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Getter
@Setter
@ToString
public class EmployeePOJO {

    private Long id;
    private String name;
    private Integer age;
    //@JsonSerialize(using= ToStringSerializer.class)
    private Long guid;
}
