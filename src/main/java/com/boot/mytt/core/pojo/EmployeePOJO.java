package com.boot.mytt.core.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
