package com.boot.mytt.core.validation.validator;

import com.boot.mytt.core.validation.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private String regexp;

    @Override
    public void initialize(Phone constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        } else {
            return value.matches(regexp);
        }
    }

}
