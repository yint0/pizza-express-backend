package com.ecnu.pizzaexpress.utils;

import com.ecnu.pizzaexpress.constants.DateFormatConstants;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yerunjie on 2019-02-03
 *
 * @author yerunjie
 */
public class DateTimeUtils {

  private DateTimeUtils() {
  }

  private static ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<>();

  private static ThreadLocal<SimpleDateFormat> DATE_TIME_FORMAT = new ThreadLocal<>();

  private static ThreadLocal<SimpleDateFormat> TIME_FORMAT = new ThreadLocal<>();

  private static SimpleDateFormat getDateFormat() {
    if (DATE_FORMAT.get() == null) {
      DATE_FORMAT.set(new SimpleDateFormat(DateFormatConstants.DATE_FORMAT));
    }
    return DATE_FORMAT.get();
  }

  private static SimpleDateFormat getTimeFormat() {
    if (TIME_FORMAT.get() == null) {
      TIME_FORMAT.set(new SimpleDateFormat(DateFormatConstants.TIME_FORMAT));
    }
    return TIME_FORMAT.get();
  }

  private static SimpleDateFormat getDateTimeFormat() {
    if (DATE_TIME_FORMAT.get() == null) {
      DATE_TIME_FORMAT.set(new SimpleDateFormat(DateFormatConstants.DATE_TIME_FORMAT));
    }
    return DATE_TIME_FORMAT.get();
  }

  public static String formatDateTime(Date date) {
    if (date == null) {
      return "";
    }
    return getDateTimeFormat().format(date);
  }

  public static String formatDate(Date date) {
    if (date == null) {
      return "";
    }
    return getDateFormat().format(date);
  }

  public static String formatTime(Date date) {
    if (date == null) {
      return "";
    }
    return getTimeFormat().format(date);
  }


  public static List<Date> getDates(Date startDate, Date endDate) {
    if (startDate.after(endDate)) {
      throw new RuntimeException();
    }
    LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    List<Date> dates = new ArrayList<>();
    for (LocalDate localDate = start; !localDate.isAfter(end); localDate = localDate.plusDays(1)) {
      Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
      dates.add(Date.from(instant));
    }
    return dates;
  }

}
