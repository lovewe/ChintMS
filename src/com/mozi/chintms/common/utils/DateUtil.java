package com.mozi.chintms.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 通用日历处理类
 */
public class DateUtil {

	@SuppressWarnings("unused")
	private static final int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static final String day = "d";
	public static final String week = "w";
	public static final String month = "m";
	public static final String year = "y";

	private DateUtil() {
	}

	public static long getCurrentTimeSec() {
		return System.currentTimeMillis() / 1000L;
	}

	/* 日期整型：到毫秒,1970-1-01 00:00:00.000 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static long converseStringToMs(String s,String type) {

		SimpleDateFormat sdf = new SimpleDateFormat(type);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(s));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long creatTasktimeMS = c.getTimeInMillis();
		return creatTasktimeMS;
	}
	

	public static Date parseSecLongToDate(long time) {
		return new Date(time * 1000L);
	}

	public static String formatSecTime(long time) {
		Date date = parseSecLongToDate(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 得到当前日期的整数形式,yyyyMMdd。
	 * 
	 * @return 当前日期的整数形式
	 */
	public static int getDate() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回时间 格式:时/分/秒 hhnnss
	 * 
	 * @return 时间的整数形式
	 */
	public static int getTime() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		return hour * 10000 + minute * 100 + second;
	}

	/**
	 * 得到当前日期之后某月的日期,如当前日期2008年10月1日,2月后为2008年12月1日
	 * 
	 * @param monthCount
	 *            月份数
	 * @return 日期
	 */
	public static Calendar addMonth(int monthCount) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONDAY) + monthCount);
		return cal;
	}

	/**
	 * 得到当前日期之后某月的日期,如当前日期2008年10月1日,2月后为2008年12月1日
	 * 
	 * @param monthCount
	 *            月份数
	 * @return 日期
	 */
	public static Calendar addMonth(Date date, int monthCount) {
		Calendar cal = convertCalendar(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONDAY) + monthCount);
		return cal;
	}

	/**
	 * 指定增加类型和数目的,当前日期之后的某个日期
	 * 
	 * @param field
	 *            时间类型,如年、月、日、时、分、秒
	 * @param amount
	 *            时间数目
	 * @return 日期
	 */
	public static Calendar add(int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.set(field, cal.get(field) + amount);
		return cal;
	}

	/**
	 * 格式化输出
	 * 
	 * @param cal
	 *            日期对象
	 * @param sformat
	 *            输出格式
	 * @return 字符串格式日期
	 */
	public static String format(Calendar cal, String sformat) {
		SimpleDateFormat dateFormat;
		try {
			dateFormat = new SimpleDateFormat(sformat);
		} catch (IllegalArgumentException e) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return dateFormat.format(cal == null ? Calendar.getInstance().getTime() : cal.getTime());
	}

	/**
	 * 得到当前时间的java.util.Date类型的日期
	 * 
	 * @return 日期
	 */
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 得到当前时间的java.util.Date类型的日期,精确到日
	 * 
	 * @return 日期
	 */
	public static Date getNowDate() {
		Calendar now = Calendar.getInstance();
		return new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).getTime();
	}

	/**
	 * 返回精确毫秒时间
	 * 
	 * @return java.sql.Timestamp 返回精确毫秒时间
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(getNow().getTime());
	}

	/**
	 * 将数字格式的时间,转换为字符串表示,如120000 -> 12:00:00
	 * 
	 * @param time
	 *            数字格式的时间
	 * @return 字符串格式的时间
	 */
	public static String formatTime(int time) {
		int hour = time / 10000;
		int min = (time - hour * 10000) / 100;
		int sec = time - hour * 10000 - min * 100;
		String hours = hour + "";
		String mins = min + "";
		String secs = sec + "";
		if (0 <= hour && hour <= 9)
			hours = "0" + hour;
		if (0 <= min && min <= 9)
			mins = "0" + min;
		if (0 <= sec && sec <= 9)
			secs = "0" + sec;
		return hours + ":" + mins + ":" + secs;
	}

	/**
	 * 根据条件生成若干年、若干月、若干日之后的某日的整数形式日期
	 * 
	 * @param years
	 *            若干年
	 * @param months
	 *            若干月
	 * @param days
	 *            若干日
	 * @return 整数形式日期
	 */
	public int createValidDate(int years, int months, int days) {
		Calendar now = new GregorianCalendar();
		Calendar validDate = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1, now.get(Calendar.DAY_OF_MONTH));

		String year = String.valueOf(validDate.get(Calendar.YEAR) + years);
		String month = String.valueOf(validDate.get(Calendar.MONTH) + months);
		String day = String.valueOf(validDate.get(Calendar.DAY_OF_MONTH) + days);
		if (month.length() < 2)
			month = "0" + month;
		if (day.length() < 2)
			day = "0" + day;
		String nowDate = year + month + day;
		return Integer.parseInt(nowDate);
	}

	/**
	 * 生成指定天数后的日期
	 * 
	 * @param days
	 *            指定天数
	 * @return 数字形式的日期
	 */
	public static int createByDays(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(dateFormat.format(calendar.getTime()));
	}

	/**
	 * 根据传入日期生成指定天数后的日期
	 * 
	 * @param date
	 *            传入日期
	 * @param days
	 *            指定天数
	 * @return 日期
	 */
	public static Date createByDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return (Date) cal.getTime();
	}

	/**
	 * 计算两日之间的间隔天数
	 * 
	 * @param startday
	 *            开始日期
	 * @param endday
	 *            结束日期
	 * @return 间隔天数
	 */
	public static int getDays(Date startday, Date endday) {
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(startday);
		endCal.setTime(endday);
		return (int) ((endCal.getTime().getTime() - startCal.getTime().getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 根据间隔,返回对应天数
	 * 
	 * @param term
	 *            (d,D,m,M,y,Y) 间隔
	 * @return 对应天数
	 */
	public static int getTermDays(String term) {
		if (term == null || term.trim().length() == 0)
			return 0;
		int len = term.trim().length();
		int num = Integer.parseInt(len > 1 ? term.trim().substring(0, len - 1) : term.trim());
		switch (term.trim().charAt(len - 1)) {
		case 'd':
		case 'D':
			return num;
		case 'w':
		case 'W':
			return num *= 7;
		case 'm':
		case 'M':
			return num *= 30;
		case 'y':
		case 'Y':
			return num *= 365;
		default:
			return num = Integer.parseInt(term.trim());
		}
	}

	/**
	 * 根据期限间隔,生成给定日期指定天数后的日期
	 * 
	 * @param date
	 *            给定日期
	 * @param term
	 *            (d,D,m,M,y,Y) 期限间隔
	 * @return 指定天数后的日期
	 */
	public static Date createByDays(Date date, String term) {
		return createByDays(date, countDay(date, term));
	}

	/**
	 * 获得指定日期的期限间隔的实际天数,比如给定2007年2月1日,期限1月,返回28;给定2007年3月1日,期限1月,返回31
	 * 
	 * @param startDate
	 *            指定日期
	 * @param term
	 *            期限间隔
	 * @return 实际天数
	 */
	public static int countDay(Date startDate, String term) {
		if (term == null || term.trim().length() == 0)
			return 0;
		String str = term.trim().toLowerCase();
		int result = 0;
		char cha = str.charAt(str.length() - 1);
		if (47 < cha && cha < 58) {
			str = str + day;
		}
		if (str.length() == 1) {
			str = "0" + str;
		}
		try {
			result = Integer.parseInt(str.substring(0, str.length() - 1));
		} catch (Exception e) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int days = 0;
		if (str.endsWith(day)) {
			days = result;
		} else if (str.endsWith(week)) {
			days = result * 7;
		} else if (str.endsWith(month)) {
			int y = cal.get(Calendar.YEAR);
			int m = cal.get(Calendar.MONTH);
			for (int i = 0; i < result; i++) {
				if (m > 11) {
					y++;
					m = m - 12;
				}
				cal.clear();
				cal.set(Calendar.YEAR, y);
				cal.set(Calendar.MONTH, m++);
				days += cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
		} else if (str.endsWith(year)) {
			int y = cal.get(Calendar.YEAR);
			for (int i = 0; i < result; i++) {
				if (cal.get(Calendar.MONTH) < 2) {
					cal.set(Calendar.YEAR, y++);
				} else {
					cal.set(Calendar.YEAR, ++y);
				}
				days += cal.getActualMaximum(Calendar.DAY_OF_YEAR);
			}
		} else {
			days = 0;
		}
		return days;
	}

	/**
	 * 获得指定日期(字符串格式yyyy-MM-dd)的期限间隔的实际天数,
	 * 比如给定2007年2月1日,期限1月,返回28;给定2007年3月1日,期限1月,返回31
	 * 
	 * @param startDate
	 *            指定日期yyyy-MM-dd
	 * @param term
	 *            期限间隔
	 * @return 实际天数
	 */
	public static int countDay(String startDate, String term) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(startDate);
		return countDay(date, term);
	}

	/**
	 * 转换日期 Date -> 20080101
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期的字符串格式如yyyyMMdd
	 * @return 日期的字符串形式如yyyyMMdd
	 */
	public static String getDateStr(Date date, String format) {
		if (date == null)
			return null;
		format = format == null ? "yyyy-MM-dd HH:mm:ss" : format;
		if (format.equals("CNS")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] str = sdf.format(date).split("-");
			return str[0] + "年" + str[1] + "月" + str[2] + "日";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 转换日期 20080101 -> Date
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 *            日期的字符串格式如yyyyMMdd
	 * @return Date 日期
	 * @throws ParseException
	 *             日期解析异常
	 */
	public static Date getUtilDate(String dateStr, String format) {
		if (dateStr == null || dateStr.length() == 0)
			return null;
		format = format == null ? "yyyy-MM-dd HH:mm:ss" : format;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 比较时间大小
	 * 
	 * @param d1
	 *            比较的第一个日期
	 * @param d2
	 *            比较的第二个日期
	 * @return 第一个日期大于第二个日期,返回true;否则返回false
	 */
	public static boolean greatDate(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return false;
		return d1.getTime() > d2.getTime();
	}

	/**
	 * 将java.util.Date类型日期转换为java.util.Calendar类型日期
	 * 
	 * @param date
	 *            java.util.Date 类型日期
	 * @return java.util.Calendar 类型日期
	 */
	public static Calendar convertCalendar(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 将Date转换为int形 格式(yyyyMMdd)
	 * 
	 * @param date
	 *            日期对象
	 * @return 日期的整数形式
	 */
	public static int toInteger(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR) * 10000 + (c.get(Calendar.MONTH) + 1) * 100 + c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 按yyyyMMdd格式比较两个日期的大小
	 * 
	 * @param date1
	 *            比较的第一个日期
	 * @param date2
	 *            比较的第二个日期
	 * @return 两个日期相等返回0,date1大于date2返回1,date1小于date2返回-1.
	 */
	public static int compareTo(Date date1, Date date2) {
		int intDate1 = toInteger(date1);
		int intDate2 = toInteger(date2);
		return intDate1 >= intDate2 ? (intDate1 == intDate2 ? 0 : 1) : -1;
	}

	/**
	 * 按yyyyMMdd格式比较两个日期的大小
	 * 
	 * @param date1
	 *            比较的第一个日期
	 * @param date2
	 *            比较的第二个日期
	 * @return 两个日期相等返回0,date1大于date2返回1,date1小于date2返回-1.
	 */
	public static int compareTo(String date1, String date2) throws ParseException {
		int intDate1 = toInteger(getUtilDate(date1, "yyyy-MM-dd"));
		int intDate2 = toInteger(getUtilDate(date2, "yyyy-MM-dd"));
		return intDate1 >= intDate2 ? (intDate1 == intDate2 ? 0 : 1) : -1;
	}

	/**
	 * 按yyyyMMdd G:i:s格式比较两个日期的大小
	 * 
	 * @param date1
	 *            比较的第一个日期
	 * @param date2
	 *            比较的第二个日期
	 * @return 两个日期相等返回0,date1大于date2返回1,date1小于date2返回-1.
	 */
	public static int compareTime(Date date1, Date date2) {
		long intDate1 = date1.getTime();
		long intDate2 = date2.getTime();
		return intDate1 >= intDate2 ? (intDate1 == intDate2 ? 0 : 1) : -1;
	}

	/**
	 * 按yyyyMMdd G:i:s格式比较两个日期的大小
	 * 
	 * @param date1
	 *            比较的第一个日期
	 * @param date2
	 *            比较的第二个日期
	 * @return 两个日期相等返回0,date1大于date2返回1,date1小于date2返回-1.
	 */
	public static int compareTime(String date1, String date2) throws ParseException {
		long intDate1 = toInteger(getUtilDate(date1, null));
		long intDate2 = toInteger(getUtilDate(date1, null));
		return intDate1 >= intDate2 ? (intDate1 == intDate2 ? 0 : 1) : -1;
	}

	/**
	 * 返回两个日期相差年数
	 * 
	 * @param start
	 * @param end
	 * @return int
	 */
	public static int yearsOf(Date start, Date end) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(start);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(end);
		return c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR) + 1;
	}

	/**
	 * 将最多6位的数字,转换成时间对象,年月日都取1。比如： <li>123030 转换为表示 公元1年1月1日 12:30:30 的日期 <li>
	 * 130 转换为表示 公元1年1月1日 00:01:30 的日期
	 * 
	 * @param s
	 *            表示时间的数字
	 * @return 日期对象
	 */
	public static Date parseIntToTime(int s) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		int[] time = new int[3];
		String sTime = String.valueOf(s);
		int step = 6 - sTime.length();
		for (int i = 0; i < step; i++) {
			sTime = "0" + sTime;
		}
		if (6 != sTime.length()) {
			return cal.getTime();
		} else {
			for (int i = 0; i < 3; i++) {
				String t = sTime.substring(i * 2, (i + 1) * 2);
				time[i] = Integer.parseInt(t);
			}
			cal.set(Calendar.HOUR_OF_DAY, time[0]);
			cal.set(Calendar.MINUTE, time[1]);
			cal.set(Calendar.SECOND, time[2]);
			return cal.getTime();
		}

	}

	/**
	 * 将时间对象转化为数字(只包括时间,不包括日期)。比如： <li>表示 公元2008年1月1日 12:30:30 的日期 转换为数字 123030
	 * <li>表示 公元2008年1月1日 00:01:30 的日期 转换为数字 130
	 * 
	 * @param cal
	 *            需要转换的时间
	 * @return 表示时间的数字
	 */
	public static int parseTimeToInt(Calendar cal) {
		if (cal == null) {
			return 0;
		}
		String sResult = "";
		int tH = cal.get(Calendar.HOUR);
		String sH = String.valueOf(tH);
		if (sH.length() == 1) {
			sH = "0" + sH;
		}
		sResult = sResult + sH;
		int tM = cal.get(Calendar.MINUTE);
		String sM = String.valueOf(tM);
		if (sM.length() == 1) {
			sM = "0" + sM;
		}
		sResult = sResult + sM;
		int tS = cal.get(Calendar.SECOND);
		String sS = String.valueOf(tS);
		if (sS.length() == 1) {
			sS = "0" + sS;
		}
		sResult = sResult + sS;
		return Integer.parseInt(sResult);
	}

	/**
	 * 将时间对象转化为数字(只包括时间,不包括日期)。比如： <li>表示 公元2008年1月1日 12:30:30 的日期 转换为数字 123030
	 * <li>表示 公元2008年1月1日 00:01:30 的日期 转换为数字 130
	 * 
	 * @param d
	 *            需要转换的时间
	 * @return 表示时间的数字
	 */
	public static int parseTimeToInt(Date d) {
		Calendar cal = convertCalendar(d);
		return parseTimeToInt(cal);
	}

	/**
	 * 简化日期型，去掉时间显示
	 * 
	 * @param date
	 * @return
	 */
	public static String getSimpleDate(Date date) {
		if (date == null)
			return "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 简化日期型，去掉时间显示
	 * 
	 * @param date
	 * @return
	 */
	public static String getSimpleDate(String date) {
		return getSimpleDate(getUtilDate(date, "yyyy-MM-dd"));
	}

	/**
	 * 简化日期型，去掉时间显示
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEasyDate(Date date) {
		return getUtilDate(getSimpleDate(date), "yyyy-MM-dd");
	}

	/**
	 * 获取当月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 功能:判断是否为日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 昨天
	 * 
	 * @return
	 */
	public static String getYesterdayFormat() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 判断当前时间是否在这个时间段内
	 * 
	 * @param t1
	 *            =时间1 格式 HH:mm:ss
	 * @param t2
	 *            =时间2 格式 HH:mm:ss
	 * @return 如果是返回true,否则false
	 */
	public static boolean isInTime(String t1, String t2) {
		String ct = getDateStr(new Date(), "HH:mm:ss");
		String cts[] = ct.split(":");
		int ctss = (Integer.parseInt(cts[0]) * 60 + Integer.parseInt(cts[1])) * 60 + Integer.parseInt(cts[2]);
		String t1s[] = t1.split(":");
		int t1ss = (Integer.parseInt(t1s[0]) * 60 + Integer.parseInt(t1s[1])) * 60 + Integer.parseInt(t1s[2]);
		String t2s[] = t2.split(":");
		int t2ss = (Integer.parseInt(t2s[0]) * 60 + Integer.parseInt(t2s[1])) * 60 + Integer.parseInt(t2s[2]);
		if (t1ss >= t2ss) { //
			if (ctss >= t1ss || ctss <= t2ss)
				return true;
		} else {
			if (ctss >= t1ss && ctss <= t2ss)
				return true;
		}
		return false;
	}

	/**
	 * 将指定日期转化为星期几
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getWeek(String date, String format) {
		String[] day = new String[] { "日", "一", "二", "三", "四", "五", "六" };
		format = format == null ? "yyyy-MM-dd HH:mm:ss" : format;
		DateFormat df = new SimpleDateFormat(format);// 输入的日期格式必须是这种
		Scanner s = new Scanner(date);
		while (true) {
			try {
				Date d = df.parse(s.nextLine());// 把字符串转化成日期
				Calendar cal = convertCalendar(d);
				s.close();
				return date + " " + "星期" + day[cal.get(Calendar.DAY_OF_WEEK) - 1];
			} catch (ParseException e) {

			}
		}
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss从String 转换成Date 类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseStringToDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTime();
	}
}
