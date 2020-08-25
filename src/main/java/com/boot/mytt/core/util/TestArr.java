package com.boot.mytt.core.util;

/**
 * @author renwq
 * @since 2020/6/24 13:00
 */
public class TestArr {
    public static void main(String[] args) {
        int arr[] = {3 ,5, 4, 2, 1};
        // 调用示例
        // 时间复杂度是O(n^2)
        System.out.println(getXMin(arr, 1));
        System.out.println(getXMin(arr, 2));
        System.out.println(getXMin(arr, 3));
        System.out.println(getXMin(arr, 4));
        System.out.println(getXMin(arr, 5));
    }

    // 取第k小的值，在数组中
    private static int getXMin(int arr[], int k) {
        // 判断k是否大约给定的数组长度
        if (k > arr.length) {
            throw new IllegalArgumentException("无效参数");
        }
        int max = 0;
        if (k == arr.length) {
            for (int z=0; z<k; z++) {
                if (arr[z] > max) {
                    max = arr[z];
                }
            }
            // 返回结果
            return max;
        }
        // 新建中间数组，初始值与原始数组的前k个值相同
        int tmpArr[] = new int[k];
        for (int t=0; t<k; t++) {
            tmpArr[t] = arr[t];
        }
        // 具体判断逻辑实现
        for (int out=k; out<arr.length; out++) {
            int tmpMax = tmpArr[0];
            int tmpMaxIndex = 0;
            int tmpMin = 0;
            boolean flag = true;
            for (int in=0; in<k; in++) {
                if (tmpArr[in] > tmpMax) {
                    tmpMax = tmpArr[in];
                    tmpMaxIndex = in;// 内层中最大值的索引
                }
                // 当原始数组有更小的值时
                if (arr[out] < tmpArr[in] && flag) {
                    tmpMin = arr[out];// 外层中更小的值
                    flag = false;
                }
            }
            // 如果flag状态发生改变，说明外层有更小的值，此时替换内层最大值
            if (!flag) {
                tmpArr[tmpMaxIndex] = tmpMin;
            }
        }
        // 通过上面的逻辑，中间数组的数据已变为原始数组前k个小的数
        for (int z=0; z<k; z++) {
            // 取中间数组最大的值，即为原数组中第k小的值
            if (tmpArr[z] > max) {
                max = tmpArr[z];
            }
        }
        // 返回结果
        return max;
    }
}
