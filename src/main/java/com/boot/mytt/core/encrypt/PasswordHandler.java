package com.boot.mytt.core.encrypt;

import java.util.Random;


/**
 * @author renwq
 * @date 2020/5/25
 */
public class PasswordHandler {

    private PasswordHandler() {

    }

    private static class InnerClass {
        private static PasswordHandler passwordHandler = new PasswordHandler();
    }

    public static PasswordHandler getInstance() {
        return InnerClass.passwordHandler;
    }

    /**
     * create slat md5 password
     *
     * @param password
     * @return
     */
    public String encrypt(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        password = MD5Handler.getInstance().encrypt(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * verify password
     *
     * @param password
     * @param md5Password
     * @return
     */
    public boolean verify(String password, String md5Password) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5Password.charAt(i);
            cs1[i / 3 * 2 + 1] = md5Password.charAt(i + 2);
            cs2[i / 3] = md5Password.charAt(i + 1);
        }
        String salt = new String(cs2);
        return MD5Handler.getInstance().encrypt(password + salt).equals(new String(cs1));
    }

}
