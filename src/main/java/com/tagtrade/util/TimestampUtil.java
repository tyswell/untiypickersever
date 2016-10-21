package com.tagtrade.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class TimestampUtil {

  private static final SimpleDateFormat timestampFormat = new SimpleDateFormat(
      "dd/MM/yyyy HH:mm:ss", Locale.US);

  private static final SimpleDateFormat timestampThaiFormat = new SimpleDateFormat(
      "dd/MM/yyyy HH:mm:ss", ThaiLocale.get());

  private static final SimpleDateFormat timestampSqlFormat = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss:SSS", ThaiLocale.get());

  public static Timestamp stringToTimestamp(String str) {
    if (str == null) {
      throw new IllegalArgumentException();
    }

    try {
      return DateUtil.date2Timestamp(timestampFormat.parse(str));
    }
    catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String timestampToString(Timestamp timestamp) {
    if (timestamp == null) {
      throw new IllegalArgumentException();
    }
    return timestampFormat.format(DateUtil.date2Timestamp(timestamp));
  }

  public static Timestamp stringToTimestampThaiFormat(String str) {
    if (str == null) {
      throw new IllegalArgumentException();
    }

    try {
      return DateUtil.date2Timestamp(timestampThaiFormat.parse(str));
    }
    catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String timestampToStringThaiFormat(Timestamp timestamp) {
    if (timestamp == null) {
      throw new IllegalArgumentException();
    }
    return timestampThaiFormat.format(DateUtil.date2Timestamp(timestamp));
  }

  public static Timestamp buildTimestampFromDate(Date date) {
    Calendar cld = Calendar.getInstance();
    cld.setTime(date);
    cld.set(Calendar.AM_PM, Calendar.AM);
    cld.set(Calendar.HOUR, 0);
    cld.set(Calendar.MINUTE, 0);
    cld.set(Calendar.SECOND, 0);
    cld.set(Calendar.MILLISECOND, 0);
    Timestamp timestamp = new Timestamp(cld.getTimeInMillis());
    timestamp.setNanos(0);
    return timestamp;
  }

  public static String formatToSqlString(Timestamp timestamp) {
    return timestampSqlFormat.format(timestamp);
  }

  public static Timestamp parseToTimetamp(String dateTime) {
    if(dateTime==null) {
      return null;
    }
    String dateTimeString=new String(dateTime.trim());

    //System.out.println("Date Time:"+dateTimeString);
    try {
      //System.out.println("**********************************");
      int seperatorIndex = 0;

      //day
      seperatorIndex = dateTimeString.indexOf("/");
      if(seperatorIndex<0) {
        return null;
      }
      String dateString = dateTimeString.substring(0,seperatorIndex);
      int date = Integer.parseInt(dateString);
      dateTimeString = dateTimeString.substring(seperatorIndex+1);
      //System.out.println("dateString:"+dateString);
      //System.out.println("dateTimeString:"+dateTimeString);

      int month = 0;
      int year = 0;

      String yearString = "";

      seperatorIndex = dateTimeString.indexOf("/");
      if(seperatorIndex<0) {
        month = Integer.parseInt(dateTimeString.substring(0,2));
        //System.out.println("month:"+month);
        yearString = (dateTimeString.substring(2,4));
        //System.out.println("yearString:"+yearString);
        dateTimeString = dateTimeString.substring(4);
        //System.out.println("dateTimeString:"+dateTimeString);
      }
      else {
        //month
        String monthString = dateTimeString.substring(0,seperatorIndex);
        //System.out.println("monthString:"+monthString);
        month = Integer.parseInt(monthString);
        dateTimeString = dateTimeString.substring(seperatorIndex+1);

        //year
        yearString = dateTimeString.substring(0,4);
        dateTimeString = dateTimeString.substring(4);
      }

      //try to guess whether year is in BE or AD
      if(yearString.length()==2) {
        if(Integer.parseInt(yearString)>80 || Integer.parseInt(yearString)<3) {
          yearString = "30"+yearString;
        }
        if(Integer.parseInt(yearString)<40) {
          yearString = "20"+yearString;
        }
        else {
          yearString = "25"+yearString;
        }
      }
      //System.out.println("yearString:"+yearString);

      year = Integer.parseInt(yearString);

      //convert year to Anno Domini
      if(year>2900) {
        year-=1086;
      }
      else if(year>2400) {
          year-=543;
      }
      //System.out.println("final year:"+year);

      int hour=0, minute=0, second=0;

      if(dateTimeString.length()!=0) {
        while(dateTimeString.charAt(0)==' ' || dateTimeString.charAt(0)==':') {
          dateTimeString = dateTimeString.substring(1);
        }

        String delimiter = "";
        if(dateTimeString.indexOf(":")>0) {
          delimiter = ":";
        }
        else if (dateTimeString.indexOf(".")>0) {
          delimiter = ".";
        }
        else {
          return null;
        }

        StringTokenizer tokenizer = new StringTokenizer(dateTimeString,delimiter);
        if(tokenizer.countTokens()!=3) {
          return null;
        }
        for(int i=0; i<3; i++) {
          String token = tokenizer.nextToken();
          if(i==0) {
            hour=Integer.parseInt(token);
          }
          else if(i==1) {
            minute=Integer.parseInt(token);
          }
          else if(i==2) {
            second=Integer.parseInt(token);
          }
        }
      }

      Calendar cld = Calendar.getInstance();

      cld.set(Calendar.YEAR, year);
      cld.set(Calendar.MONTH, month-1);
      cld.set(Calendar.DATE, date);
      cld.set(Calendar.HOUR_OF_DAY, hour);
      cld.set(Calendar.MINUTE, minute);
      cld.set(Calendar.SECOND, second);

      Timestamp time = new Timestamp(cld.getTimeInMillis());
      time.setNanos(0);
      return time;
    }
    catch(NumberFormatException ne) {
      ne.printStackTrace();
      return null;
    }
    catch(StringIndexOutOfBoundsException se) {
      se.printStackTrace();
      return null;
    }
  }

  public static String formatDateThai(Timestamp timestamp) {
    if (timestamp == null) {
      return "";
    }

    Date date = DateUtil.time2Date(timestamp);
    return DateUtil.formatDateThai(date);
  }

  public static int getDiffDaysFromToday(Timestamp timestamp) {
    Date currentDate = new Date();
    long time1 = currentDate.getTime();
    long time2 = timestamp.getTime();
    long diff = (time1 - time2) / (60*60*24*1000);
    return (int) diff;
  }

  public static Timestamp getNow() {
  	Calendar calendar = Calendar.getInstance();
  	return new Timestamp(calendar.getTimeInMillis());
  }

}
