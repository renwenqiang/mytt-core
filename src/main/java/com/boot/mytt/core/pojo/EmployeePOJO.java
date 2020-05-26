package com.boot.mytt.core.pojo;

import com.boot.mytt.core.validation.annotation.Telephone;
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
    @Telephone
    private String name;
    private Integer age;
    //@JsonSerialize(using= ToStringSerializer.class)
    private Long guid;
}
