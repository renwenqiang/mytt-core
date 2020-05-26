package com.boot.mytt.core.validation.annotation;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author renwq
 * @date 2020/5/25
 */
@ConstraintComposition(CompositionType.AND)
@Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
@NotBlank
@Documented
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface Telephone {
    String message() default "手机号校验错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}