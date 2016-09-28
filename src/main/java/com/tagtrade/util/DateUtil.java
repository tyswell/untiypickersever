package com.tagtrade.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

  private static final SimpleDateFormat monthYearFormat = new SimpleDateFormat(
      "MMMMM yyyy", new Locale("th", "TH"));

  private static final SimpleDateFormat dateFormatThaiAbbr = new SimpleDateFormat(
      "dd MMM yyyy", new Locale("th", "TH"));

  private static final SimpleDateFormat dateFormatThaiLong = new SimpleDateFormat(
      "dd MMMMM yyyy", new Locale("th", "TH"));

  private static final SimpleDateFormat dateFormatThai = new SimpleDateFormat(
      "dd/MM/yyyy", new Locale("th", "TH"));

  private static final SimpleDateFormat dateTimeFormatThai = new SimpleDateFormat(
      "dd/MM/yyyy HH:mm", new Locale("th", "TH"));

  private static final SimpleDateFormat sqlDateFormat = new SimpleDateFormat(
      "yyyy-MM-dd", Locale.US);

  private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.US);

  private static final SimpleDateFormat timeFormatThai = new SimpleDateFormat(
      "HH:mm", new Locale("th", "TH"));

  private static final SimpleDateFormat timeFormatThaiLong = new SimpleDateFormat(
      "HH:mm:ss", new Locale("th", "TH"));

  private static final int WEEK = 7;

  // Simple value of millisecs per day (no time zone, no offset, etc).
  private static final long MILLISECS_PER_DAY = 1000*60*60*24;

  public static int getYearThai(Date date) {
    return getDayMonthYearThai(date)[2];
  }

  public static int[] getDayMonthYearThai(Date date) {
    int[] result = getDayMonthYearEng(date);
    result[2] += 543;

    return result;
  }

  public static int[] getDayMonthYearEng(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    int[] result = new int[3];
    result[0] = calendar.get( Calendar.DAY_OF_MONTH );
    result[1] = calendar.get( Calendar.MONTH ) + 1;
    result[2] = calendar.get( Calendar.YEAR );

    return result;
  }

  public static int[] getHourMinuteSecond(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    int[] result = new int[3];
    result[0] = calendar.get( Calendar.HOUR_OF_DAY );
    result[1] = calendar.get( Calendar.MINUTE );
    result[2] = calendar.get( Calendar.SECOND );

    return result;
  }

  public static Date getDateThai(int day, int month, int year) {
    return getDateEng(day, month, year - 543);
  }

  public static Date getDateEng(int day, int month, int year) {
    if ((day < 1) || (day > 31) || (month < 1) || (month > 12) || (year < 1)) {
      throw new IllegalArgumentException(day + ", " + month + ", " + year);
    }

    return new GregorianCalendar(year, month - 1, day).getTime();
  }

  public static String getYearMonthDayEng(String dayMonthYearThai) {
    Date date = parseDateThai(dayMonthYearThai);

    return new java.sql.Date( date.getTime() ).toString();
  }

  public static String formatDateThai(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    return dateFormatThai.format(date);
  }

  public static String formatDateTimeThai(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    return dateTimeFormatThai.format(date);
  }

  public static Date parseDateThai(String str) {
    if (str == null) {
      throw new IllegalArgumentException();
    }

    try {
      return dateFormatThai.parse(str);
    }
    catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static Date parseDateTimeThai(String str) {
    if (str == null) {
      throw new IllegalArgumentException();
    }

    try {
      return dateTimeFormatThai.parse(str);
    }
    catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String formatDateTime(Date date){
    if (date == null) {
      throw new IllegalArgumentException();
    }

    return dateTimeFormat.format(date);
  }

  public static boolean isValidDateThai(String str) {
    try {
      parseDateThai(str);

      return true;
    }
    catch (RuntimeException e) {
      return false;
    }
  }

  // Saturday or Sunday
  public static  boolean isNormalHoliday(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    int dayOfWeek = calendar.get( Calendar.DAY_OF_WEEK );

    return ((dayOfWeek == Calendar.SATURDAY) || (dayOfWeek == Calendar.SUNDAY));
  }

  public static Date getNow() {

    GregorianCalendar calendar = new GregorianCalendar();

    return calendar.getTime();
  }

  public static java.sql.Date getSQLDateNow() {

    GregorianCalendar calendar = new GregorianCalendar();

    return new java.sql.Date(calendar.getTime().getTime());
  }

  public static java.sql.Date toSqlDate(Date utilDate) {
    if(utilDate==null) {
      return null;
    }
    return new java.sql.Date(utilDate.getTime());
  }

  @SuppressWarnings("deprecation")
  public static Integer getCurrentYearThai() {
    GregorianCalendar calendar = new GregorianCalendar();
    return (calendar.getTime().getYear()) + 1900 + 543;
  }

  public static Timestamp getTimestampNow() {

    GregorianCalendar calendar = new GregorianCalendar();

    return date2Timestamp(calendar.getTime());
  }

  public static Date yesterday(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    calendar.add(Calendar.DATE, -1);

    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static Date getTimeNow(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    calendar.set(Calendar.YEAR, 1970);
    calendar.set(Calendar.MONTH, 0);
    calendar.set(Calendar.DAY_OF_MONTH, 1);

    return calendar.getTime();
  }

  public static Date addDay(Date date, int day){
    return addDate(date, day, 0, 0);
  }

  public static Date addMonth(Date date, int month){
    return addDate(date, 0, month, 0);
  }

  public static Date addYear(Date date, int year) {
    return addDate(date, 0, 0, year);
  }

  private static Date addDate(Date date, int day, int month, int year){
    if (date == null) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    calendar.add(Calendar.DATE, day);
    calendar.add(Calendar.MONTH, month);
    calendar.add(Calendar.YEAR, year);

    return calendar.getTime();
  }

  /*public static void main(String[] args) {
    System.out.println( getYearMonthDayEng("28/02/2549") );

    Date date = new Date();

    System.out.println( formatDateThai(date) );
    System.out.println( parseDateThai("14/02/2549") );
  }*/

  public static String getDayMonthYearThai(String yearMonthDayEng) {
    Date date = parseDateEng(yearMonthDayEng);
    return dateFormatThai.format(date);
  }

  public static Date parseDateEng(String yearMonthDayEng) {
    if (yearMonthDayEng == null) {
      throw new IllegalArgumentException();
    }

    try {
      return sqlDateFormat.parse(yearMonthDayEng);
    }
    catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String formatDateEng(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }
    return sqlDateFormat.format(date);
  }

  public static Date tomorrow(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    calendar.add(Calendar.DATE, +1);

    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static Date getDatePartOnly( Date date ) {

    if ( date == null ) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static boolean verifyDateFromTpiServer(String strDate) {

    if ((strDate == null)||(strDate.equals(""))) {
      throw new IllegalArgumentException();
    }
    int year = (Integer.parseInt(strDate.substring(6,10))-543);
    strDate = strDate.substring(0,6) + String.valueOf(year);
    Date currentDate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date;
    try {
      date = format.parse(strDate);
      if (date.after(currentDate)) {
        return false;
      }
      return true;
    }
    catch (ParseException e) {
      return false;
    }
  }

  public static boolean isDateOverDue(Date startDate, Date endDate) {
    Date now = getNow();
    String nowDesc = formatDateThai(now);
    String expireDateDesc = formatDateThai(endDate);

    return isDateOverDue(nowDesc, expireDateDesc);
  }

  public static boolean isDateOverDue(String startDate, String endDate) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date dateStart;
    Date dateEnd;

    try {
      dateStart = format.parse(startDate);
      dateEnd = format.parse(endDate);
      if (dateEnd.before(dateStart)) {
        return true;
      }
      return false;
    }
    catch (ParseException e) {
      return false;
    }

  }

  public static boolean equals(Date date1, Date date2) {
    if ((date1 == null) && (date2 == null)) {
      return true;
    }

    if ((date1 == null) || (date2 == null)) {
      throw new IllegalArgumentException(date1 + ", " + date2);
    }

    Date dateOnly1 = getDatePartOnly(date1);
    Date dateOnly2 = getDatePartOnly(date2);

    return dateOnly1.equals(dateOnly2);
  }

  public static Timestamp date2Timestamp(Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }
    return  new Timestamp( date.getTime());
  }

  public static Date time2Date(Timestamp timestamp) {
    if (timestamp == null) {
      throw new IllegalArgumentException();
    }
    return new Date(timestamp.getTime());
  }

  public static java.sql.Date time2DateSql(Timestamp timestamp) {
    if (timestamp == null) {
      throw new IllegalArgumentException();
    }
    return new java.sql.Date(timestamp.getTime());
  }

  /**
   * Get the number of days between endDate and startDate (endDate-startDate).
   * This method use simple calculation and doesn't consider time zone or
   * any other issues.
   *
   * @param startDate
   * @param endDate
   * @return
   */
  public static long dayDiff(Date startDate, Date endDate) {
    if ( (startDate == null) || (endDate == null)) {
      throw new IllegalArgumentException("input date is null");
    }

    return (endDate.getTime() - startDate.getTime()) / MILLISECS_PER_DAY;
  }


  /**
   * get first date of month from input Date
   * @param inputDate
   * @return first date of month
   */
  public static Date getFirstDayOfMonth(Date inputDate) {


  //Get the time right now
    Calendar calendar = Calendar.getInstance();
    //Set it to the first day of whatever month it is now
    calendar.set(Calendar.DAY_OF_MONTH, 1);



    return getDatePartOnly(calendar.getTime());

  }


  public static Date getFirstDayOfMonthFromDate(Date inputDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(inputDate);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTime();
  }

  public static Integer getDayOfMonth(Date date) {
    if ( date == null ) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    return calendar.get(Calendar.DAY_OF_MONTH);

  }

  /**
   * get year number
   * @param date
   * @return year number
   */
  public static Integer getYear(Date date) {
    if ( date == null ) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    return calendar.get(Calendar.YEAR);

  }

  /**
   * get month number
   * @param date
   * @return month number
   *
   */
  public static Integer getMonth(Date date) {
    if ( date == null ) {
      throw new IllegalArgumentException();
    }
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH);
  }


  /**
   * get last month number
   * @param date
   * @return last month number
   */
  public static Integer getLastMonth(Date date) {
    if ( date == null ) {
      throw new IllegalArgumentException();
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    calendar.add(Calendar.MONTH, -1);

    return calendar.get(Calendar.MONTH) + 1;
  }

  public static Long difDateFromNow(Date dateNow, Date date) {
    if( dateNow != null && date != null){
      long diff = dateNow.getTime() - date.getTime();
      return -(diff / (24 * 60 * 60 * 1000));
    }
    else{
      return null;
    }

  }


  //****************************************************************************

  /**
   * รูปแบบ เช่น มิถุนายน 2553
   * @param date
   * @return
   */
  public static String formatMonthYearForReport(Date date) {
    if ( date == null ) {
      throw new IllegalArgumentException();
    }

    return monthYearFormat.format(date);
  }

  /**
   * รูปแบบ เช่น 31 ม.ค. 2553
   * @param date
   * @return
   */
  public static String formatDateForReport(Date date) {
    if ( date == null ) {
      throw new IllegalArgumentException();
    }

    return dateFormatThaiAbbr.format(date);
  }

  /**
   * รูปแบบ เช่น 31 มกราคม  2553
   * @param date
   * @return
   */
  public static String formatLongDateForReport(Date date) {
    if ( date == null ) {
      throw new IllegalArgumentException();
    }

    return dateFormatThaiLong.format(date);
  }

  public static Date getLastDateOfMonth(Date date) {
    if (date == null) {
      return null;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    int lastDate = calendar.getActualMaximum(Calendar.DATE);
    calendar.set(Calendar.DATE, lastDate);

    return calendar.getTime();
  }

  //****************************************************************************

  private static final String[] MONTH_IN_THAI_STR = {
    "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
    "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"
  };

  /**
   * แสดง เดือน และ พ.ศ.แบบไทย  รูปแบบเต็ม  เช่น  มกราคม 2553
   */
  public static String getMonthYearThaiFull(Date date) {
    if (date == null) {
      return null;
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR) + 543;

    StringBuilder sb = new StringBuilder(50);
    sb.append(MONTH_IN_THAI_STR[month] + " ");
    sb.append(year);

    return sb.toString();
  }

  /**
   * แสดงวัน เดือน และ พ.ศ.แบบไทย  รูปแบบเต็ม  เช่น   01มกราคม  2553
   */
  public static String getDateMonthYearThaiFull(Date date) {
    if (date == null) {
      return null;
    }

    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);

    return calendar.get(Calendar.DATE) + " " +getMonthYearThaiFull(date);
  }

  public static String formatTime(Date date){
    if (date == null) {
      throw new IllegalArgumentException();
    }

    return timeFormatThai.format(date);
  }

  public static String formatTimeLong(Date date){
    if (date == null) {
      throw new IllegalArgumentException();
    }

    return timeFormatThaiLong.format(date);
  }

  public static int subtractDays(Date date1, Date date2){
    GregorianCalendar gc1 = new GregorianCalendar();
    gc1.setTime(date1);
    GregorianCalendar gc2 = new GregorianCalendar();
    gc2.setTime(date2);

    int days1 = 0;
    int days2 = 0;
    int maxYear = Math.max(gc1.get(Calendar.YEAR), gc2.get(Calendar.YEAR));

    GregorianCalendar gctmp = (GregorianCalendar) gc1.clone();
    for (int f = gctmp.get(Calendar.YEAR);  f < maxYear;  f++)
      {days1 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);  gctmp.add(Calendar.YEAR, 1);}

    gctmp = (GregorianCalendar) gc2.clone();
    for (int f = gctmp.get(Calendar.YEAR);  f < maxYear;  f++)
      {days2 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);  gctmp.add(Calendar.YEAR, 1);}

    days1 += gc1.get(Calendar.DAY_OF_YEAR) - 1;
    days2 += gc2.get(Calendar.DAY_OF_YEAR) - 1;

    return Math.abs(days1 - days2);
  }

  public static int subtractDaysToWeek(Date date1, Date date2){
    int subDate = subtractDays( date1, date2 );

    return subDate/WEEK;
  }

  public static int compareDateOnlyDatePart(Date date1, Date date2) {
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTime(date1);
    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTime(date2);
    if(new Integer(calendar1.get(Calendar.YEAR)).compareTo(new Integer(calendar2.get(Calendar.YEAR)))!=0 ) {
      return new Integer(calendar1.get(Calendar.YEAR)).compareTo(new Integer(calendar2.get(Calendar.YEAR))) ;
    }
    if(new Integer(calendar1.get(Calendar.MONTH)).compareTo(new Integer(calendar2.get(Calendar.MONTH)))!=0 ) {
      return new Integer(calendar1.get(Calendar.MONTH)).compareTo(new Integer(calendar2.get(Calendar.MONTH))) ;
    }
    return new Integer(calendar1.get(Calendar.DATE)).compareTo(new Integer(calendar2.get(Calendar.DATE))) ;
  }
}
