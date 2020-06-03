package com.boot.mytt.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.mytt.core.entity.ClassInfo;
import com.boot.mytt.core.entity.Classes;
import org.springframework.stereotype.Component;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Component
public interface ClassMapper extends BaseMapper<Classes> {

    Classes getClasses(int id);

    Classes getClasses2(int id);

    ClassInfo getClass3(int id);

    ClassInfo getClass4(int id);
}
