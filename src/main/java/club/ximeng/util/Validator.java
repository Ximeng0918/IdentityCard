package club.ximeng.util;

import java.time.LocalDate;
import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段验证器
 *
 * @author liu xm cover Looly
 * @date 2021/3/23 13:27
 */
public class Validator {
    /**
     * 生日
     */
    public final static Pattern BIRTHDAY = Pattern.compile("^(\\d{2,4})([/\\-.年]?)(\\d{1,2})([/\\-.月]?)(\\d{1,2})日?$");

    /**
     * 验证是否为生日<br>
     * 只支持以下几种格式：
     * <ul>
     * <li>yyyyMMdd</li>
     * <li>yyyy-MM-dd</li>
     * <li>yyyy/MM/dd</li>
     * <li>yyyy.MM.dd</li>
     * <li>yyyy年MM月dd日</li>
     * </ul>
     *
     * @param value 值
     * @return 是否为生日
     */
    public static boolean isBirthday(CharSequence value) {
        final Matcher matcher = BIRTHDAY.matcher(value);
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(3));
            int day = Integer.parseInt(matcher.group(5));
            return isBirthday(year, month, day);
        }
        return false;
    }

    /**
     * 验证是否为生日
     *
     * @param year  年，从1900年开始计算
     * @param month 月，从1开始计数
     * @param day   日，从1开始计数
     * @return 是否为生日
     */
    public static boolean isBirthday(int year, int month, int day) {
        // 验证年
        int thisYear = LocalDate.now().getYear();
        if (year < 1900 || year > thisYear) {
            return false;
        }

        // 验证月
        if (month < 1 || month > 12) {
            return false;
        }

        // 验证日
        if (day < 1 || day > 31) {
            return false;
        }
        // 检查几个特殊月的最大天数
        if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        if (month == 2) {
            // 在2月，非闰年最大28，闰年最大29
            return day < 29 || (day == 29 && Year.of(year).isLeap());
        }
        return true;
    }
}
