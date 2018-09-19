package cn.koboro.points.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.*;


public class Util {

	/**
	 * 根据出生日期 判断所属的生命周期。 婴儿期（出生～1岁） 小儿期（1～3岁） 幼儿期（3～6岁） 少年期（7～17岁） 青年期（18～44岁）
	 * 中年期（45～59岁） 年青老年期（60～74岁） 老年期（75～89岁） 长寿老年期（90岁以上）
	 * 
	 * @param birthdate
	 * @return
	 */
	public static String getLifecycle(Date birthdate) throws Exception {
		String res = "";
		Date currentDate = new Date();// 当前日期
		Calendar c = Calendar.getInstance();
		c.setTime(birthdate);
		// 判断
		c.add(Calendar.YEAR, 1);
		Date date2 = c.getTime();
		if (date2.getTime() > currentDate.getTime()) {
			return "婴儿期";
		}
		c.add(Calendar.YEAR, 2);
		Date date3 = c.getTime();
		if (date3.getTime() > currentDate.getTime()) {
			return "小儿期";
		}
		c.add(Calendar.YEAR, 3);
		Date date4 = c.getTime();
		if (date4.getTime() > currentDate.getTime()) {
			return "幼儿期";
		}
		c.add(Calendar.YEAR, 11);
		Date date5 = c.getTime();
		if (date5.getTime() > currentDate.getTime()) {
			return "少年期";
		}
		res = judgeDate(currentDate, c);
		return res;
	}

	/**
	 * （接上文）
	 * 
	 * @param currentDate
	 * @param c
	 * @return
	 */
	private static String judgeDate(Date currentDate, Calendar c) {
		String res;
		c.add(Calendar.YEAR, 27);
		Date date6 = c.getTime();
		if (date6.getTime() > currentDate.getTime()) {
			return "青年期";
		}
		c.add(Calendar.YEAR, 15);
		Date date7 = c.getTime();
		if (date7.getTime() > currentDate.getTime()) {
			return "中年期";
		}
		c.add(Calendar.YEAR, 15);
		Date date8 = c.getTime();
		if (date8.getTime() > currentDate.getTime()) {
			return "年青老年期";
		}
		c.add(Calendar.YEAR, 15);
		Date date9 = c.getTime();
		if (date9.getTime() > currentDate.getTime()) {
			res = "老年期";
		} else {
			res = "长寿老年期";
		}
		return res;
	}

	/**
	 * 根据生命周期 获取最早的出生日期和最晚的出生日期
	 * 
	 * @param lifeCycle
	 * @return
	 */
	public static Object[] getLifeCycleStartAndEndDate(String lifeCycle) {
		// Object[] objs = new Object[2];
		Object[] objs = null;
		Date currentDate = new Date(); // 当前日期
		Calendar startC = Calendar.getInstance();// 最早的出生日期
		Calendar endC = Calendar.getInstance(); // 最晚的出生日期
		startC.setTime(currentDate);
		endC.setTime(currentDate);
		if (lifeCycle.equals("婴儿期") || lifeCycle.equals("小儿期")
				|| lifeCycle.equals("幼儿期") || lifeCycle.equals("少年期")) {
			// 所做操作方法化
			objs = judegLifeCycleOne(lifeCycle, startC, endC);
		}
		if (lifeCycle.equals("青年期") || lifeCycle.equals("中年期")
				|| lifeCycle.equals("年青老年期") || lifeCycle.equals("老年期")) {
			// 所做操作方法化
			objs = judegLifeCycleTwo(lifeCycle, startC, endC);
		}
		if (lifeCycle.equals("长寿老年期")) {
			objs = new Object[2];
			endC.add(Calendar.YEAR, -90);
			startC.add(Calendar.YEAR, -500);// 足够大
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		return objs;
	}

	/**
	 * 为getLifeCycleStartAndEndDate 新建 判断生理周期（婴儿，小儿，幼儿，少年）
	 * 
	 * @param lifeCycle
	 * @param startC
	 * @param endC
	 * @return
	 */
	public static Object[] judegLifeCycleOne(String lifeCycle, Calendar startC,
			Calendar endC) {
		Object[] objs = new Object[2];
		if (lifeCycle.equals("婴儿期")) {
			startC.add(Calendar.YEAR, -1);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		if (lifeCycle.equals("小儿期")) {
			endC.add(Calendar.YEAR, -1);
			startC.add(Calendar.YEAR, -3);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		if (lifeCycle.equals("幼儿期")) {
			endC.add(Calendar.YEAR, -3);
			startC.add(Calendar.YEAR, -6);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		if (lifeCycle.equals("少年期")) {
			endC.add(Calendar.YEAR, -6);
			startC.add(Calendar.YEAR, -18);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		return objs;
	}

	/**
	 * 为getLifeCycleStartAndEndDate 新建 判断生理周期（青年，中年，年青老年期，老年期）
	 * 
	 * @param lifeCycle
	 * @param startC
	 * @param endC
	 * @return
	 */
	public static Object[] judegLifeCycleTwo(String lifeCycle, Calendar startC,
			Calendar endC) {
		Object[] objs = new Object[2];
		if (lifeCycle.equals("青年期")) {
			endC.add(Calendar.YEAR, -18);
			startC.add(Calendar.YEAR, -45);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		if (lifeCycle.equals("中年期")) {
			endC.add(Calendar.YEAR, -45);
			startC.add(Calendar.YEAR, -60);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		if (lifeCycle.equals("年青老年期")) {
			endC.add(Calendar.YEAR, -60);
			startC.add(Calendar.YEAR, -75);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		if (lifeCycle.equals("老年期")) {
			endC.add(Calendar.YEAR, -75);
			startC.add(Calendar.YEAR, -90);
			objs[0] = startC.getTime();
			objs[1] = endC.getTime();
		}
		return objs;
	}

	public static void main(String[] args) {
		// System.out.println(toMessageDigest("1234567890"));
		Map<String, String> maps = new LinkedHashMap<String, String>();
		maps.put("1", "张三");
		maps.put("2", "李四");
		maps.put("3", "王五");
		maps.put("4", "赵六");
		System.out.println("LinkedHashMap(有序):");
		// 1
		// Iterator<Map<String,String>> it = maps.entrySet().iterator();
		// while(it.hasNext())
		// {
		// Map.Entry entity = (Entry) it.next();
		// System.out.println("[ key = " + entity.getKey() +
		// ", value = " + entity.getValue() + " ]");
		// }
		// 2
		// for(String key:maps.keySet()){
		// System.out.println(maps.get(key));
		// }
		// 3
		// for (Map.Entry<String, String> entry : maps.entrySet()) {
		// System.out.println("key= " + entry.getKey() + " and value= " +
		// entry.getValue());
		// }
		// 4
		for (String v : maps.values()) {
			System.out.println("value= " + v);
		}
	}

	// 解密字符串
	public static String gerResult(String s) {
		int key = 520;
		StringBuffer result = new StringBuffer();
		for (int i = 0, len = (s.toString().length()) / ((key + "")).length(); i < len; i++) {
			int num = Integer.parseInt(s.substring(i * 3, i * 3 + 3));
			result.append((char) (num ^ key));
		}
		return result.toString();
	}

	// 生成随机的字符串
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 年龄段查询
	 * 
	 * @param birthdate
	 * @param value
	 * @return
	 */
	public static boolean getageSegment(Date birthdate, String value) {
		Date date = new Date();
		if (value.indexOf("0-30") != -1) {
			Calendar c = Calendar.getInstance();
			c.setTime(birthdate);
			c.add(Calendar.YEAR, 30);
			Date futureDate = c.getTime();
			if (futureDate.getTime() > date.getTime())
				return true;
		}
		if (value.indexOf("30-40") != -1) {
			Calendar cYi = Calendar.getInstance();
			cYi.setTime(birthdate);
			cYi.add(Calendar.YEAR, 30);
			Date futureDateOne = cYi.getTime();
			cYi.add(Calendar.YEAR, 10);
			Date futureDateTwo = cYi.getTime();
			if (futureDateOne.getTime() <= date.getTime()
					&& futureDateTwo.getTime() > date.getTime())
				return true;
		}
		if (value.indexOf("40-50") != -1) {
			Calendar cTwo = Calendar.getInstance();
			cTwo.setTime(birthdate);
			cTwo.add(Calendar.YEAR, 40);
			Date futureDateThree = cTwo.getTime();
			cTwo.add(Calendar.YEAR, 10);
			Date futureDatefroe = cTwo.getTime();
			if (futureDateThree.getTime() <= date.getTime()
					&& futureDatefroe.getTime() > date.getTime())
				return true;
		}
		if (value.indexOf("50-") != -1) {
			Calendar cSan = Calendar.getInstance();
			cSan.setTime(birthdate);
			cSan.add(Calendar.YEAR, 50);
			Date futureDateWu = cSan.getTime();
			if (futureDateWu.getTime() < date.getTime())
				return true;
		}
		if (Validator.isEmpty(value))
			return true;
		return false;
	}
	 /***
	 * 给个日期 和 数字
	 * @param beginTime
	 * @param n
	 * @return beginTime的n天之后的日期
	 */
	public static Date getDateDaysLater(Date beginTime, int n) throws Exception {
		DateFormat df = DateFormat.getInstance();
		Calendar calendar = df.getCalendar();
		calendar.setTime(beginTime);
		calendar.add(Calendar.DAY_OF_YEAR, n);
		return calendar.getTime();
	}

	/***
	 * 给个日期 和 数字
	 * @param beginTime
	 * @param n
	 * @return beginTime的n月之后的日期
	 */
	public static Date getDateMonthsLater(Date beginTime, int n)
			throws Exception {
		DateFormat df = DateFormat.getInstance();
		Calendar calendar = df.getCalendar();
		calendar.setTime(beginTime);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

	/***
	 * 给个日期 和 数字
	 * @param beginTime
	 * @param n
	 * @return beginTime的n年之后的日期
	 */
	public static Date getDateYearsLater(Date beginTime, int n)
			throws Exception {
		DateFormat df = DateFormat.getInstance();
		Calendar calendar = df.getCalendar();
		calendar.setTime(beginTime);
		calendar.add(Calendar.YEAR, n);
		return calendar.getTime();
	}

	/**
	 * 判断最后一个日期是否在前两个日期之间
	 * 
	 * @param beginTime
	 * @param n
	 * @return
	 * @throws Exception
	 *             false:表示不在 true:表示在
	 */
	public static boolean judgeBetweenDate(Date oneTime, Date twoTime,
			Date judgeTime) throws Exception {
		long one = oneTime.getTime();
		long two = twoTime.getTime();
		long judge = judgeTime.getTime();
		if ((one <= judge && judge <= two) || (two <= judge && judge <= one)) {
			return true;
		}
		return false;
	}

	/**
	 * 公共计算 一个日期后几天的日期或者几个月的日期或者几年的日期(一直往上累加，直到大于今天日期)
	 * 
	 * @param beginDate
	 * @param count
	 * @param countType
	 *            ：年、月、日、时、空值
	 * @return
	 * @throws Exception
	 */
	public static Date getPlanDate(Date beginDate, int count, String countType)
			throws Exception {
		Date curDate = new Date();// 当前时间
		if (countType.equals("年")) {
			while (true) {
				beginDate = Util.getDateYearsLater(beginDate, count);
				if (beginDate.getTime() >= curDate.getTime()) {
					return beginDate;
				}
			}
		} else if (countType.equals("月")) {
			while (true) {
				beginDate = Util.getDateMonthsLater(beginDate, count);
				if (beginDate.getTime() >= curDate.getTime()) {
					return beginDate;
				}
			}
		} else if (countType.equals( "日")) {
			while (true) {
				beginDate = Util.getDateDaysLater(beginDate, count);
				if (beginDate.getTime() >= curDate.getTime()) {
					return beginDate;
				} }
		}else{//如果频率为空，设为6个月
			while(true){
				beginDate = Util.getDateMonthsLater(beginDate, 6);
				if(beginDate.getTime() >= curDate.getTime()){
					return beginDate;
				} }
		}
	}

	/**
	 * 拷贝对象
	 * 
	 * @param object
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Object copy(Object object) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Object objectCopy = null;
		if (null == object) return null;
		Class<?> classType = object.getClass();
		Field fields[] = classType.getDeclaredFields();// 取原对象的属性
		// 创建赋值对象
		objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
		Field field = null;
		String suffixMethodName;
		Method getMethod, setMethod;
		Object value = null;
		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			suffixMethodName = field.getName().substring(0, 1).toUpperCase()
					+ (field.getName().length() > 1 ? field.getName().substring(1) : "");
			if (suffixMethodName.equals( "SerialVersionUID")) {
				continue;
			}
			getMethod = classType.getMethod("get" + suffixMethodName, new Class[] {});
			setMethod = classType.getMethod("set" + suffixMethodName, new Class[] { field.getType() });
			value = getMethod.invoke(object, new Object[] {});
			if (null == value) {
				if (field.getType().getName().equalsIgnoreCase("java.lang.String")) {
					setMethod.invoke(objectCopy, new Object[] { "" });
				}
			} else {
				setMethod.invoke(objectCopy, new Object[] { value });
			} }
		return objectCopy;
	}

	/**
	 * 计算两个日期相差的年份
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getYears(Date beginDate, Date endDate) {
		if (beginDate != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(beginDate);
			int one = c.get(Calendar.YEAR);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			int two = calendar.get(Calendar.YEAR);
			return two - one;
		}
		return 0;
	}

	/**
	 * 根据单位计数秒
	 * 
	 * @param unit
	 * @return
	 */
	public static long getSeconds(String unit) {
		if (Validator.isEmpty(unit)) {
			return 0;
		}
		long i = 0;
		if (unit.equals("时")) {
			i = 60 * 60;
		} else if (unit.equals("日")) {
			i = 24 * 60 * 60;
		} else if (unit.equals("月")) {
			i = 30 * 24 * 60 * 60;
		} else if (unit.equals("年")) {
			i = 12 * 30 * 24 * 60 * 60;
		}
		return i;
	}

	/**
	 * 根据加年 或加月或加日 获取某一个时间 time:次数 unit:年或月或日 5年,4月
	 */
	public static Date getSpecialDate(Date date, String time, String unit) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (unit.equals("年")) {
			cal.add(Calendar.YEAR, Integer.parseInt(time)); // 年
		}
		if (unit.equals("月")) {
			cal.add(Calendar.MONTH, Integer.parseInt(time));// 月
		}
		if (unit.equals("日")) {
			cal.add(Calendar.DATE, Integer.parseInt(time)); // 日
		}
		return cal.getTime();
	}

	/**
	 * 
	 * @param files
	 * @return
	 */
	public static Date getDateAssignTime(Date date, int days) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, days);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}


	/**
	 * 一体机接口登录类型
	 * 
	 * @param number
	 * @return
	 */
	public static String loginType(String number) {
		String res = "";
		if (number.equals("1")) {
			res = "身份证";
		} else if (number.equals("2")) {
			res = "手机号";
		} else if (number.equals("3")) {
			res = "社保卡";
		} else if (number.equals("4")) {
			res = "条形码";
		} else if (number.equals("5")) {
			res = "IC卡";
		}
		return res;
	}

	/**
	 * 一体机接口体重类型
	 * 
	 * @param number
	 * @return
	 */
	public static String weightType(String number) {
		String res = "";
		if (number.equals("-1")) {
			res = "消瘦";
		} else if (number.equals("0")) {
			res = "正常";
		} else if (number.equals("1")) {
			res = "超重";
		} else if (number.equals("2")) {
			res = "肥胖";
		}
		return res;
	}

	// （app端）根据身高体重计算BMI和体重类型
	public static String[] weightType(Float height, Float weight) {
		String[] array = new String[2];
		Float bmi = 0F;
		String res = "";
		if (height != null && weight != null && height.intValue() != 0
				&& weight.intValue() != 0) {
			Float h = height / 100;
			if (h.floatValue() > 0) {// 防止身高不填写
				bmi = weight / (h * h);
			}
			BigDecimal b = new BigDecimal(bmi);
			bmi = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			if (bmi < 18.5) {
				res = "过轻";
			} else if (bmi >= 18.5 && bmi < 25) {
				res = "正常";
			} else if (bmi >= 25 && bmi < 28) {
				res = "过重";
			} else if (bmi >= 28 && bmi <= 32) {
				res = "肥胖";
			} else {
				res = "非常肥胖";
			}
		}
		array[0] = bmi.toString();
		array[1] = res;
		return array;
	}

	/**
	 * 检查建议-常规项目的类别转换
	 */
	public static String checkAdviceDetectCategories(String detectCategories,
			List<String> categories) {
		String res = "";
		if (Validator.isNotNull(detectCategories)) {
			if (detectCategories.indexOf("临床专项检查") != -1) {
				res = "clinical";
			} else if (detectCategories.indexOf("日常监测") != -1) {
				res = "daytoday";
			} else if (detectCategories.indexOf("功能检测") != -1) {
				res = "function";
			}
		} else if (categories != null && categories.size() > 0) {
			if (categories.contains("临床专项检查")) {
				res = "clinical";
			} else if (categories.contains("日常监测")) {
				res = "daytoday";
			} else if (categories.contains("功能检测")) {
				res = "function";
			}

		}
		return res;
	}

	public static String judgeCardiovascularRiskLevel(String content) {
		String res = "";
		if (content.contains("低危")) {
			res = "心血管疾病风险低危";
		} else if (content.contains("中危")) {
			res = "心血管疾病风险中危";
		} else if (content.contains("高危")) {
			res = "心血管疾病风险高危";
		}
		return res;
	}



	/**
	 * 根据运动项目获取其系数(旧。17个项目。新旧系数不一样)
	 * 
	 * @param item
	 * @return
	 */
	public static double getSportsCoefficient(String item) {// 该方法暂时不用,以新的为主2017/4/15
		double d = 0D;
		if (Validator.isEmpty(item)) {
		} else if (item.equals("保龄球")) { d = 0.0501;
		} else if (item.equals("排球")) { d = 0.0501;
		} else if (item.equals("太极拳")) { d = 0.0585;
		} else if (item.equals("乒乓球")) { d = 0.0668;
		} else if (item.equals("瑜伽")) { d = 0.0668;
		} else if (item.equals("步行")) { d = 0.0716;
		} else if (item.equals("跳舞")) { d = 0.0724;
		} else if (item.equals("羽毛球")) { d = 0.0752;
		} else if (item.equals("自行车")) { d = 0.0835;
		} else if (item.equals("高尔夫球")) { d = 0.0835;
		} else if (item.equals("网球")) { d = 0.1058;
		} else if (item.equals("篮球")) { d = 0.1086;
		} else if (item.equals("轮滑") || item.equals("轮滑旱冰")) { d = 0.1169;
		} else if (item.equals("足球")) { d = 0.1169;
		} else if (item.equals("游泳")) { d = 0.1378;
		} else if (item.equals("跑步")) { d = 0.1536;
		} else if (item.equals("跳绳")) { d = 0.1670;
		}
		return d;
	}

	/**
	 * 根据运动项目获取其系数(新。15个项目。新旧系数不一样)-----20170313
	 * 
	 * @param item
	 * @return
	 */
	public static double getSportsCoefficientNew(String item) {
		double d = 0.0;
		if (item.equals("步行")) { d = 0.0716;
		} else if (item.equals("跑步")) { d = 0.1536;
		} else if (item.equals("自行车")) { d = 0.0835;
		} else if (item.equals("乒乓球")) { d = 0.0716;
		} else if (item.equals("网球")) { d = 0.1058;
		} else if (item.equals("羽毛球")) { d = 0.0752;
		} else if (item.equals("高尔夫球")) { d = 0.0835;
		} else if (item.equals("篮球")) { d = 0.1086;
		} else if (item.equals("足球")) { d = 0.1169;
		} else if (item.equals("跳绳")) { d = 0.1503;
		} else if (item.equals("跳舞")) { d = 0.0724;
		} else if (item.equals("游泳")) { d = 0.1378;
		} else if (item.equals("瑜伽")) { d = 0.0716;
		} else if (item.equals("太极拳")) { d = 0.0716;
		} else if (item.equals("轮滑旱冰") || item.equals("轮滑")) { d = 0.1169;
		}
		return d;
	}

	/**
	 * 根据运动项目获取其运动强度
	 * 
	 * @param item
	 * @return
	 */
	public static String getSportsIntensity(String item) {
		String d = "";
		if (Validator.isEmpty(item)) {
		} else if (item.equals("保龄球") || item.equals("排球")
				|| item.equals("太极拳") || item.equals("乒乓球")
				|| item.equals("瑜伽") || item.equals("步行") || item.equals("跳舞")
				|| item.equals("羽毛球") || item.equals("羽毛球")
				|| item.equals("自行车") || item.equals("高尔夫球")) {
			d = "中等强度";
		} else if (item.equals("网球") || item.equals("篮球") || item.equals("轮滑")
				|| item.equals("轮滑旱冰") || item.equals("足球")
				|| item.equals("游泳") || item.equals("跑步") || item.equals("跳绳")) {
			d = "高强度";
		}
		return d;
	}

	// 获取疾病所属系统
	public static List<String> getSystemList() {
		List<String> list = new ArrayList<String>();
		list.add("营养代谢性疾病");
		list.add("循环系统疾病");
		list.add("神经系统疾病");
		list.add("消化系统疾病");
		list.add("呼吸系统疾病");
		list.add("内分泌系统疾病");
		list.add("泌尿系统疾病");
		list.add("生殖系统疾病");
		list.add("骨骼关节疾病");
		list.add("精神系统疾病");
		list.add("五官科疾病");
		list.add("肿瘤");
		list.add("其它系统疾病");
		return list;
	}
	/**
	 * 减重人群疾病判断(优先)
	 * 
	 * @return
	 */
	public static List<String> getCurrentDiagnosis() {
		List<String> list = new ArrayList<String>();
		list.add("动脉硬化");
		list.add("高血压(2级)");
		list.add("高血压(3级)");
		list.add("糖尿病");
		list.add("冠心病");
		list.add("痛风");
		list.add("甲减");
		list.add("甲亢");
		list.add("消化性溃疡");
		list.add("子宫肌瘤");
		list.add("关节炎");
		list.add("中重度慢性阻塞性肺病");
		return list;
	}

	/**
	 * 减重人群疾病判断(不用优先)
	 * 
	 * @return
	 */
	public static List<String> getCurrentDiagnosisTwo() {
		List<String> list = new ArrayList<String>();
		list.add("肥胖");
		list.add("脂肪肝");
		list.add("高脂血症");
		list.add("高血压(1级)");
		list.add("前列腺增生");
		list.add("乳腺增生");
		list.add("神经衰弱");
		list.add("甲状腺结节");
		list.add("慢性胃炎");
		list.add("骨质疏松");
		list.add("肺部疾病(得到良好控制)");
		list.add("过敏性鼻炎");
		list.add("湿疹");
		list.add("颈椎病");
		list.add("慢性咽炎");
		return list;
	}

	/**
	 * 传入文件大小（字节） 返回相应的单位
	 */
	public static String getPrintSize(long size) {
		// 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
			return String.valueOf(size) + "B";
		} else {
			size = size / 1024;
		}
		// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		// 因为还没有到达要使用另一个单位的时候
		// 接下去以此类推
		if (size < 1024) {
			return String.valueOf(size) + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			// 因为如果以MB为单位的话，要保留最后1位小数，
			// 因此，把此数乘以100之后再取余
			size = size * 100;
			return String.valueOf((size / 100)) + "."
					+ String.valueOf((size % 100)) + "MB";
		} else {
			// 否则如果要以GB为单位的，先除于1024再作同样的处理
			size = size * 100 / 1024;
			return String.valueOf((size / 100)) + "."
					+ String.valueOf((size % 100)) + "GB";
		}
	}
	/**
	 * 判断项目是否是固定范围项目，如人体成分
	 * @param itemName
	 * @return
	 */
	public static boolean judgeItem(String itemName) {
		if ("人体成分".equals(itemName)) {
			return false;
		}
		return true;
	}

	/**
	 * 中石油一体机-肌肉量判断标准一样但是范围显示不一样
	 * @param height
	 * @param range
	 * @param sex
	 * @return
	 */
    private static String handleJirouliangForBC420(Float height, String range,String sex) {
    	if ("男".equals(sex)) {
			if (height < 160) {// 身高<160 38.5~46.5
				range = "42.5±4.0";
			} else if (height > 160 && height <= 170) {
				range = "48.2±4.2";
			} else if (height > 170) {// 身高>170 49.4~59.4
				range = "54.4±5.0";
			}
		} else {// 女性：身高<150 29.1~34.7 身高:150-160 32.9~37.5 身高>160 36.5~42.5
			if (height < 150) {// 身高<150 29.1~34.7
				range = "31.9±2.8";
			} else if (height >= 150 && height < 160) {
				range = "35.2±2.3";
			} else if (height >= 160) {// 身高>160 36.5~42.5
				range = "39.5±3.0";
			}
		}
		return range;
	}

	/**
	 * 获取long值
	 * @param original
	 * @return
	 */
	public static Long getLongNull(String param){
		try {
			if(param!=null){
	            return Long.parseLong(param);			
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	
	
	
	
}
