package com.boot.mytt.core.util;
 
/**
 * 请实现函数，把我们日常使用的大写的人民币金额转化为数字
 * 不考虑角分，只精确到圆(整)，注该函数将被执行100万次，请考虑效率
 * @use 
 * @ProjectName gyjb
 * @Author <a href="mailto:mhmyqn@qq.com">mikan</a></br>
 * @Date 2013-3-27 上午01:01:45 </br>
 * @FullName com.mmq.MoneyString2Number.java </br>
 * @JDK 1.6.0 </br>
 * @Version 1.0 </br>
 */
public class MoneyString2Number {
    /**
     * 大写数字
     */
    private static final String NUMBER_CHARACTER_STRING = "零壹贰叁肆伍陆柒捌玖";
    /**
     * 基数字符
     */
    private static final String RADICES_CHARACTER_STRING = "拾佰仟万亿";
    /**
     * 基数数值
     */
    private static final int[] NUMBER_RADICES = new int[] { 10, 100, 1000, 10000, 100000000 };
    
    /**
     * 把大写的人民币金额转化为数字，不考虑角分，只精确到圆(整)
     * @param moneyString
     * @return
     */
    public static long transform(String moneyString) {
    	long result = 0;
    	int index = moneyString.indexOf("圆");
    	if (index != -1) {
    		moneyString = moneyString.substring(0, index + 1);//去掉角分
    	}
    	result = handleYi(moneyString);
    	return result;
    }
    
    /**
     * 按亿分成多个部分分别转化
     * @param moneyString
     * @return
     */
    private static long handleYi (String moneyString) {
    	long result = 0;
    	String[] monStringYiArr = moneyString.split("亿");
    	int length = monStringYiArr.length;
    	for (int i = 0; i < length; i++) {
    		String subMoneyString = monStringYiArr[i];
    		// 由于按'亿'分割之后，'亿'被去掉了，所以需要添加上
    		if (!(i == length - 1 && !moneyString.endsWith("亿"))) {
    			subMoneyString += "亿";
    		}
    		result += handleLing(subMoneyString);
    	}
    	return result;
    }
    
    /**
     * 按零分成多个部分分别转化
     * @param moneyString
     * @return
     */
    private static long handleLing(String moneyString) {
    	long result = 0;
    	String[] monStringArr = moneyString.split("零");
    	for (String subMoneyString : monStringArr) {
    		result += toNumber(subMoneyString);
    	}
    	return result;
    }
	
    /**
     * 把大写的人民币金额转化为数字
     * @param subMoneyString
     * @return
     */
	private static long toNumber(String subMoneyString) {
		long intermediateResult = 0;
		char[] moneyArr = subMoneyString.toCharArray();
		
		int index = 0;
		while (index < subMoneyString.length()) {
			if (moneyArr[index] == '零' || moneyArr[index] == '整' || moneyArr[index] == '圆') {
				index++;
				continue;
			}
			// 如果当前处理的位是基数位，则直接乘以之前的结果，继续处理下一位
			int indexOfRadices = RADICES_CHARACTER_STRING.indexOf(moneyArr[index]);
			if (indexOfRadices != -1) {
				intermediateResult = (intermediateResult == 0 ? 1 : intermediateResult) * NUMBER_RADICES[indexOfRadices];
				index++;
				continue;
			}
			
			// 处理数字位
			int indexOfDigit = NUMBER_CHARACTER_STRING.indexOf(moneyArr[index]);
			long tmp = indexOfDigit;
			// 如果要处理的金额字符串只有一位数字，直接返回这位数字
			if (subMoneyString.length() == 1) {
				return indexOfDigit;
			}
			
			// 当前数字位的一下位是 '整' 或 '圆'，则返回结果
			int indexOfRadices_next = RADICES_CHARACTER_STRING.indexOf(moneyArr[index + 1]);
			if (moneyArr[index + 1] == '整' || moneyArr[index + 1] == '圆') {
				intermediateResult += tmp;
				return intermediateResult;
			}
			
			// 如果当前数字位的下一位是基数位，则先计算结果，
			// 如果基数位是'拾'且基数位的一下位是数字，则继续处理下一位
			if (indexOfRadices_next != -1) {
				tmp *= NUMBER_RADICES[indexOfRadices_next];
				if ((index + 2) < subMoneyString.length()) {
					int indexOfDigit_next = NUMBER_CHARACTER_STRING.indexOf(moneyArr[index + 2]);
					if (moneyArr[index + 1] == '拾' && indexOfDigit_next != -1) {
						tmp += indexOfDigit_next;
						index++;
					}
				}
				index += 2;
				intermediateResult += tmp;
			}
			
		}
		return intermediateResult;
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(transform("壹万贰仟叁佰肆拾伍"));//12345
		System.out.println(transform("佰万"));//1000000
		System.out.println(transform("壹佰"));//100
		System.out.println(transform("壹"));//1
		System.out.println(transform("壹亿"));//100000000
		System.out.println(transform("壹佰万零叁佰肆拾伍圆整"));//1000345
		System.out.println(transform("壹佰亿零叁仟肆佰伍拾陆圆整"));//10000003456
		System.out.println(transform("壹万贰仟叁佰肆拾亿零肆拾伍万零壹圆整"));//1234000450001
		System.out.println(transform("壹拾亿零壹圆整"));//1000000001
		System.out.println(transform("壹佰贰拾叁万圆整"));//1230000
		System.out.println(transform("壹亿贰仟叁佰肆拾伍万圆整"));//123450000
		System.out.println(transform("贰万亿"));//2000000000000
		System.out.println(transform("零"));//0
		System.out.println("用时：" + (System.currentTimeMillis() - start) + "毫秒");
	}
}