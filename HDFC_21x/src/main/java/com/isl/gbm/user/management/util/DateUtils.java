/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.user.management.util;

import java.util.Calendar;
import java.util.Date;

/**
 * The Class DateUtils.
 */
public final class DateUtils {

    /**
     * Adds the days.
     *
     * @param d    the d
     * @param days the days
     * @return the date
     */
    public static Date addDays(final Date d, final long days) {
        Date argDate = new Date(d.getTime());
        argDate.setTime(argDate.getTime() + (days * 1000L * 60L * 60L * 24L));
        return argDate;
    }

    /**
     * Gets the end date. It appends the day ending hours with the date.
     * <p>
     * For example, this method will return a date like Tue Mar 06 23:59:59 IST 2018
     *
     * @param date the date
     * @return the end date
     */
    public static Date getEndDate(final Date date) {
        if (null == date) {
            return null;
        }
        final Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime((Date) date.clone());
        endDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endDateCalendar.set(Calendar.MINUTE, 59);
        endDateCalendar.set(Calendar.SECOND, 59);

        return endDateCalendar.getTime();
    }

    /**
     * Gets the start date. It appends the day starting hours with the date.
     * <p>
     * For example, this method will return a date like Tue Mar 06 00:00:00 IST 2018
     *
     * @param date the date
     * @return the start date
     */
    public static Date getStartDate(final Date date) {
        if (null == date) {
            return null;
        }
        final Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime((Date) date.clone());
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startDateCalendar.set(Calendar.MINUTE, 0);
        startDateCalendar.set(Calendar.SECOND, 0);

        return startDateCalendar.getTime();
    }

    /**
     * <p>
     * Checks if two calendars represent the same day ignoring time.
     * </p>
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if ((cal1 == null) || (cal2 == null)) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return ((cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA))
                && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                && (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)));
    }

    /**
     * <p>
     * Checks if two dates are on the same day ignoring time.
     * </p>
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
    public static boolean isSameDay(final Date date1, final Date date2) {
        if ((date1 == null) || (date2 == null)) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * <p>
     * Checks if a date is today.
     * </p>
     *
     * @param date the date, not altered, not null.
     * @return true if the date is today.
     * @throws IllegalArgumentException if the date is <code>null</code>
     */
    public static boolean isToday(final Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    /**
     * Instantiates a new date utils.
     */
    private DateUtils() {
        // private constructor
    }

}
