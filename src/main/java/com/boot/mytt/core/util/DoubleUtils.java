package com.boot.mytt.core.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public class DoubleUtils {

    private static final int DEF_DIV_SCALE = 10;

    public static final String format1 = "##0.00";

    public static final String format2 = "##0.0";

    public static final String format3 = "##0";

    public static final String format4 = ",####";

    public static final String format5 = "####";

    private DoubleUtils() {

    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0)
            throw new IllegalArgumentException("无效参数");
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double round(double v, int scale) {
        if (scale < 0)
            throw new IllegalArgumentException("无效参数");
        BigDecimal b1 = new BigDecimal(Double.toString(v));
        BigDecimal b2 = new BigDecimal("1");
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String double2String(double v, String type) {
        DecimalFormat df = new DecimalFormat(type);
        return df.format(v);
    }

    public static String int2String(int v, String type) {
        DecimalFormat df = new DecimalFormat(type);
        return df.format(v);
    }

    public static double string2Double(String str) {
        double num = 0;
        if (StringUtils.isEmpty(str)) {
            return num;
        }
        return Double.parseDouble(str);
    }

    public static BigDecimal string2BigDecimal(String str) {
        BigDecimal b = new BigDecimal("0");
        if (StringUtils.isEmpty(str)) {
            return b;
        }
        return new BigDecimal(str);
    }

    public static String bigDecimal2String(BigDecimal b) {
        String str = "0";
        if (Objects.isNull(b)) {
            return str;
        }
        return b.toString();
    }

    public static void main(String[] args) {
        System.out.println(int2String(32, "###.##"));
    }
}
