package cn.koboro.points.utils;

import java.math.BigDecimal;

public class PercentUtil {
	/**
	 * 结果相除四舍五入后保留整数
	 * @param divisor 除数
	 * @param dividend被除数
	 * @return
	 */
   public static int getPercent(int divisor,int dividend){
	    if(dividend==0){
	    	return 0;
	    }
	    BigDecimal v1 = new BigDecimal(divisor);
		BigDecimal v2 = new BigDecimal(dividend);
		BigDecimal v3 = v1.multiply(new BigDecimal(100)).divide(v2,2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_HALF_UP);
		return v3.intValue();
   }

	/**
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Integer mul(Integer d1, Integer d2)
	{         float f = d2/100.0f;
		// 进行乘法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(f);
		return b1.multiply(b2).intValue();
	}
}
