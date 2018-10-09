package cn.bjjoy.bms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 缺省的日期显示格式： yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    
    public static final String YYYYMMDD = "yyyy-MM-dd";
    
    /** 斜线分割年月日 */
    public static final String SLANT_LINE_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";

    public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";

    static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    
    /**
     * 年月日 时分秒
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 年月日 时分秒毫秒
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmsssss";

    public static Date getDate() {
        return new Date();
    }

    /**
     * 获取当前日期时间
     * 
     * @param format
     * @return
     */
    public static String formatDate(String format) {
        Date dt = new Date();
        return formatDate(dt, format);
    }

    /**
     * 获取当前日期时间
     * 
     * @param format
     * @return
     */
    public static String getCurrentDateZZ() {
        Date dt = new Date();
        return formatDate(dt, YYYYMMDD_ZH);
    }
    
    /**
     * 获取当前日期时间
     * 
     * @param format
     * @return
     */
    public static String getCurrentDate() {
        Date dt = new Date();
        return formatDate(dt, DEFAULT_DATE_FORMAT);
    }

    /**
     * 当前时间加秒
     * 
     * @param time
     * @return
     */
    public static Date addTime(Date date, int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, time);
        return calendar.getTime();
    }

    /**
     * 格式化日期时间为指定格式的字符串
     * 
     * @param format
     * @return
     */
    public static String formatDate(Date dt, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(dt);
    }

    /**
     * 解析字符串为Date
     * 
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static Date toDate(String date) {

        if (date == null)
            return null;

        try {
            date = date.replace("/", "-");

            if (RegexUtils.checkDate(date)) {
                return parseDate(date, "yyyy-MM-dd");
            } else if (RegexUtils.checkDateTime(date)) {
                return parseDate(date, "yyyy-MM-dd HH:mm:ss");
            }
            
            Date date1 = parseDate(date, DEFAULT_DATE_FORMAT);
            
            if (null == date1) {
                date1 = parseDate(date, YYYYMMDDHHMM);
            }
            
            if (null == date1) {
                date1 = parseDate(date, YYYYMMDD);
            }
            return date1;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期时间比较，若d1大于d2，则返回true
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean dateCompare(Date d1, Date d2) {
        if (d1.getTime() > d2.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 日期时间比较，若d1大于d2，则返回true
     * 
     * @param d1
     * @param d2
     * @param format
     * @return
     * @throws ParseException
     */
    public static Boolean dateCompare(String d1, String d2, String format) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date1 = sf.parse(d1);
        Date date2 = sf.parse(d2);
        return dateCompare(date1, date2);
    }

    /**
     * 计算当前时间后延一段时间之后的时间点
     * 
     * @param minute
     *            单位为分钟
     * @param format
     * @return
     */
    public static String calDateStr(int minute, String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date(date.getTime() + minute * 60 * 1000));// 初始化时间为当前系统时间+1
    }

    /**
     * 检查日期字符串是否符合格式定义
     * 
     * @param dateStr
     * @param format
     * @return
     */
    public static Boolean checkPattern(String dateStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            formatter.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
     * 
     * @param date
     *            基准日期
     * @param days
     *            增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(Date date, int days) {
        return add(date, days, Calendar.DATE);
    }

    /**
     * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数。 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
     * 
     * @param date
     *            基准日期
     * @param months
     *            增加的月份数
     * @return 增加以后的日期
     */
    public static Date addMonths(Date date, int months) {
        return add(date, months, Calendar.MONTH);
    }

    /**
     * @param date
     *            基准日期
     * @param years
     *            增加的年数
     * @return 增加以后的日期
     */
    public static Date addYears(Date date, int years) {
        return add(date, years, Calendar.YEAR);
    }

    /**
     * 内部方法。为指定日期增加相应的天数或月数
     * 
     * @param date
     *            基准日期
     * @param amount
     *            增加的数量
     * @param field
     *            增加的单位，年，月或者日
     * @return 增加以后的日期
     */
    private static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(field, amount);

        return calendar.getTime();
    }

    
    /**
     * 计算两个日期相差小时数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one
     *            第一个日期数，作为基准
     * @param two
     *            第二个日期数，作为比较
     * @return 两个日期相差小时数
     */
    public static Long diffHours(Date one, Date two) {
        return (one.getTime() - two.getTime()) / (3600 * 1000);
    }
    
    /**
     * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one
     *            第一个日期数，作为基准
     * @param two
     *            第二个日期数，作为比较
     * @return 两个日期相差天数
     */
    public static Long diffDays(Date one, Date two) {
        return (one.getTime() - two.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one
     *            第一个日期数，作为基准
     * @param two
     *            第二个日期数，作为比较
     * @return 两个日期相差月份数
     */
    public static Integer diffMonths(Date one, Date two) {

        Calendar calendar = Calendar.getInstance();

        // 得到第一个日期的年分和月份数
        calendar.setTime(one);
        int yearOne = calendar.get(Calendar.YEAR);
        int monthOne = calendar.get(Calendar.MONDAY);

        // 得到第二个日期的年份和月份
        calendar.setTime(two);
        int yearTwo = calendar.get(Calendar.YEAR);
        int monthTwo = calendar.get(Calendar.MONDAY);

        return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
    }

    /**
     * 将一个字符串用给定的格式转换为日期类型。<br>
     * 注意：如果返回null，则表示解析失败
     * 
     * @param datestr
     *            需要解析的日期字符串
     * @param pattern
     *            日期字符串的格式，默认为“yyyyMMdd”的形式
     * @return 解析后的日期
     */
    public static Date parse(String datestr, String pattern) {
        Date date = null;

        if (null == pattern || "".equals(pattern)) {
            pattern = DEFAULT_DATE_FORMAT;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            //
        }

        return date;
    }

    /**
     * 返回给定日期中的月份中的最后一天
     * 
     * @param date
     *            基准日期
     * @return 该月最后一天的日期
     */
    public static Date getMonthLastDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 将日期设置为下一月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);

        // 减去1天，得到的即本月的最后一天
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    /**
     * 此方法计算时间毫秒
     * 
     * @param inVal
     * @param inVal2
     * @return
     * @throws Exception
     */
    public static Long getDateDiff(Long inVal, Long inVal2) throws Exception {
        Date date = null; // 定义时间类型
        Date date1 = null; // 定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = inputFormat.parse(inVal.toString()); // 将字符型转换成日期型
            date1 = inputFormat.parse(inVal2.toString()); // 将字符型转换成日期型
            return (Long) (date.getTime() - date1.getTime()) / 1000 / 3600 / 24; // 返回天数
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * This method generates a string representation of a date/time in the format
     * you specify on input
     *
     * @param aMask
     *            the date pattern the string is in
     * @param strDate
     *            a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     *             when String doesn't match the expected format
     */
    public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    public static String dateToString(String typeFormat, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(typeFormat);
        String str = sdf.format(date);
        return str;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long lt) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static int getYMD(String type, Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int date = 0;
        if ("year".equals(type))
            date = cal.get(Calendar.YEAR);
        if ("month".equals(type))
            date = cal.get(Calendar.MONTH) + 1;
        if ("day".equals(type))
            date = cal.get(Calendar.DATE);

        // int dow = cal.get(Calendar.DAY_OF_WEEK);
        // int dom = cal.get(Calendar.DAY_OF_MONTH);
        // int doy = cal.get(Calendar.DAY_OF_YEAR);
        return date;
    }

    public static String formatSeconds(long seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分" + second + "秒";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时" + min + "分" + second + "秒";
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天" + hour + "小时" + min + "分" + second + "秒";
                }
            }
        }
        return timeStr;
    }

    /**
     * @Title getCurrentYear
     * @Description 获取今年
     * @date 2018年2月28日 下午1:36:37
     * @auther guoyongfeng
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @Title getCurrentMonth
     * @Description 获取当前月
     * @date 2018年2月28日 下午1:37:10
     * @auther guoyongfeng
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    /**
     * @Title getCurrentWeekOfYear
     * @Description 获取当前是今年第几周
     * @date 2018年2月28日 下午1:37:10
     * @auther guoyongfeng
     */
    public static int getCurrentWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    
    /**
     * @Title getCurrentDayOfWeek
     * @Description 获取今天是周几
     * @date 2018年2月28日 下午1:37:10
     */
    public static int getCurrentDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @Title getCurrentWeekOfMonth
     * @Description 获取今天是周几
     * @date 2018年2月28日 下午1:37:10
     */
    public static String getCurrentDayOfWeekZZ() {
        Calendar calendar = Calendar.getInstance();
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    
    /**
     * @Title getCurrentWeekOfMonth
     * @Description 获取当前是今月第几周
     * @date 2018年2月28日 下午1:37:10
     * @auther guoyongfeng
     */
    public static int getCurrentWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * @Title getCurrentSeason
     * @Description 当前季度
     * @date 2018年3月8日 下午4:06:39
     * @auther guoyongfeng
     * @return
     * @return int
     */
    public static int getCurrentSeason() {
        int currentMonth = getCurrentMonth() - 1;
        switch (currentMonth) {
        // 春
        case Calendar.MARCH:
        case Calendar.APRIL:
        case Calendar.MAY:
            return 1;
        // 夏
        case Calendar.JUNE:
        case Calendar.JULY:
        case Calendar.AUGUST:
            return 2;
        // 秋
        case Calendar.SEPTEMBER:
        case Calendar.OCTOBER:
        case Calendar.NOVEMBER:
            return 3;
        // 冬
        case Calendar.DECEMBER:
        case Calendar.JANUARY:
        case Calendar.FEBRUARY:
            return 4;
        }
        return 0;
    }

    /**
     * @Title getPrevMonday
     * @Description 上周一
     * @date 2018年2月28日 下午1:32:52
     * @auther guoyongfeng
     */
    public static Date getPrevMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    /**
     * @Title getPrevSunday
     * @Description 上周日
     * @date 2018年2月28日 下午1:33:02
     * @auther guoyongfeng
     */
    public static Date getPrevSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisMonday(date));
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * @Title getThisMonday
     * @Description 本周一
     * @date 2018年2月28日 下午1:33:10
     * @auther guoyongfeng
     */
    public static Date getThisMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }
    
    public static boolean isSameWeek(Date date1, Date date2) {

        if (null == date1 || null == date2) {
            return false;
        }

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setFirstDayOfWeek(Calendar.MONDAY);
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c1.setTime(date1);
        c2.setTime(date2);

        if (c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR) && Math.abs(DateUtils.diffDays( date1,  date2)) <= 7) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isSameDay(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return false;
        }
        
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }
    
    public static Long getDiffHour(String time) {
    	Date d = null;
		try {
			d = convertStringToDate(DEFAULT_DATE_FORMAT , time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return diffHours(getDate(), d) ;
    }
    
    
}