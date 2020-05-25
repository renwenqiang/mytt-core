package com.boot.mytt.core.validation.validator;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class PasswordValidator {

    private static String regexp = "^(?![0-9]+$)(?![A-Za-z]+$)(?![,\\.#%'\\+\\*\\-:;^_`]+$)[,\\.#%'\\+\\*\\-:;^_`0-9A-Za-z]{6,15}$";

    public static boolean isValid(String value) {
        if (value == null) {
            return true;
        } else {
            return value.matches(regexp);
        }
    }
}
