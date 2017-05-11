package com.nkang.kxmoment.util;

import java.math.BigDecimal;

public class NumberUtil {
	
	/**
	 * 科学计数转正常数字
	 * @param scienceNum
	 * @return
	 */
	public static String scienceToNormal(String scienceNum) {
		BigDecimal bd = new BigDecimal(scienceNum);
		return bd.toPlainString();
	}
	
}
