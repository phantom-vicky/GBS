package com.isl.gbm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

;


@Component
public class DateUtil {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public final static String getFormattedDate(String format) {
        if (format.contains("T")) {
            Date sysDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
            return sdf.format(sysDate);
        } else {
            SimpleDateFormat f = new SimpleDateFormat(format);
            return f.format(new Date());

        }

    }

    public final static Date getUtilDate(String strdate, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(strdate);
        } catch (ParseException pe) {
            log.error("ParserException :: ", pe);
        }
        return date;
    }

    public final static String getFormatDate(String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(new Date());
    }

    /*public static  void main(String args[]){
        String dateStr = "Fri May 24 00:00:00 IST 2019";
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date date = null;
        try {
            date = (Date)formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
        System.out.println("formatedDate : " + formatedDate);
    }*/

    public static String getDate(Date date) {

        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = (cal.get(Calendar.MONTH) + 1);
        String strMonth = month > 9 ? String.valueOf(month) : "0" + month;
        int day = cal.get(Calendar.DATE);
        String strDay = day > 9 ? String.valueOf(day) : "0" + day;
        String formatedDate = strDay + "/" + strMonth + "/" + cal.get(Calendar.YEAR);
        System.out.println("formatedDate : " + formatedDate);

        return formatedDate;
    }
}
