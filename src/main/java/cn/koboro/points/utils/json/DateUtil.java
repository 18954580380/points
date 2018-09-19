package cn.koboro.points.utils.json;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xdw
 */
@Slf4j
public class DateUtil {

    public static Date age2date(int age) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        return cal.getTime();
    }


    /**
     * yyyy-MM-dd
     *
     * @return
     */
    public static Date simpleString2date(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            log.info("date转换失败", e);
        }
        return date;
    }

    /**
     * 日期比较
     */
    public static boolean dataCompare(String data) {
        if (StringUtils.isBlank(data)) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        String dataStr = df.format(new Date());
        try {
            Date dt1 = df.parse(data);//将字符串转换为date类型
            Date dt2 = df.parse(dataStr);
            if (dt1.getTime() >= dt2.getTime())//比较时间大小,如果dt1大于dt2
            {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;

    }

}
