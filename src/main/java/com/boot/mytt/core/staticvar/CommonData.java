package com.boot.mytt.core.staticvar;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renwq
 * @date 2021/2/10 12:43
 */
public class CommonData {



    //大仓库存缓存
    public static Map<String, Map<String, Double>> skucoding = new HashMap<>(); // key为： dc + "#" + sku
    //本地仓、平仓映射
    public static Map<String, String> dcmap = new HashMap<>();
    //商品转换比缓存
    public static Map<String, Map<String, Double>> codesRate = new HashMap<>();
    //铺货阶段消耗的后剩余的库存
    public static Map<String, Double> charg_act = new HashMap<>(); // key为： sku + "#" + charg
    //主副码比
    public static Map<String, Map<String, Double>> primaryRate = new HashMap<>();
}
