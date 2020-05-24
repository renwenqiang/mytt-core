package com.boot.mytt.core.util;

public class HexUtils {

    private HexUtils() {

    }

    public static void main(String[] args) {
        System.out.println(byte2Hex(hex2Byte("12")));
    }

    public static byte[] hex2Byte(String hex) {
        int m,n = 0;
        int byteLen = hex.length() / 2;
        byte[] ret = new byte[byteLen];
        for (int k=0; k<byteLen; k++) {
            m = k * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(k * 2, m) + hex.substring(m, n));
            ret[k] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }

    public static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer(bytes.length);
        String tmp = "";
        for (int k=0; k<bytes.length; k++) {
            tmp = Integer.toHexString(0xFF & bytes[k]);
            if (tmp.length() < 2)
                stringBuffer.append(0);
            stringBuffer.append(tmp.toUpperCase());
        }
        return stringBuffer.toString();
    }
}
