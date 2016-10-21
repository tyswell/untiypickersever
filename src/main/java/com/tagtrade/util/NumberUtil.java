package com.tagtrade.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class NumberUtil {

  private final static NumberFormat TOTAL_FORMATTER = new DecimalFormat("#,##0");

  public static boolean isCommonNumber(String str, int length) {
    return ((str != null) && (str.length() == length) && isAllDigit(str));
  }

  public static boolean isAllDigit(String str) {
    if ((str != null) && !str.equals("")) {
      for (int i = 0; i < str.length(); i++) {
        if (!Character.isDigit( str.charAt(i) )) {
          return false;
        }
      }
      return true;
    }
    else {
      return false;
    }
  }

  public static int [] getIntArray(String str) {
    if (isAllDigit(str)) {
      int [] result = new int[str.length()];

      for (int i = 0; i < str.length(); i++) {
        result[i] = Integer.parseInt( str.substring(i, i + 1) );
      }

      return result;
    }
    else {
      throw new IllegalArgumentException(str);
    }
  }

  public static String insertCommaTotal(Integer total) {
    if (total == null) {
      return null;
    }
    return TOTAL_FORMATTER.format(total.longValue());
  }

  public static String insertCommaTotal(Long total) {
    if (total == null) {
      return null;
    }
    return TOTAL_FORMATTER.format(total.longValue());
  }

  public static String insertCommaTotal(BigDecimal total) {
    if (total == null) {
      return null;
    }
    return TOTAL_FORMATTER.format(total.longValue());
  }

  /**
   * @author jello
   * first index is baht
   * second index is stang
   */
  public static BigDecimal [] splitBahtAndStang(BigDecimal money) {
    return money.divideAndRemainder(new BigDecimal("1.00"));
  }

  /**
   * @author jello
   * format (xxx.00 to xxx.-)
   */
  public static String insertCommaMoneyForReport(BigDecimal money) {
    BigDecimal [] moneyCalc = splitBahtAndStang(money);
    String integralPart = insertCommaIntegral(moneyCalc[0].longValue());
    String remainderPart = formatRemainderPart(moneyCalc[1],true);
    return integralPart+"."+remainderPart;
  }

  /**
   * @author jello
   * input must be in "0.xx" format
   */
  public static String formatRemainderPart(BigDecimal remainder, boolean hyphenZeroRemainder) {
    if(remainder.equals(new BigDecimal("0.00")) && hyphenZeroRemainder) {
      return "-";
    }
    String remainderString = String.valueOf(remainder);
    while (remainderString.length()<2) {
      remainderString = remainderString+"0";
    }
    return (remainderString).substring(2);
  }

  public static String insertCommaIntegral(Integer integral) {
    return insertCommaIntegral((long) integral);
  }

  public static String insertCommaIntegral(Long integral) {
    String result = "";
    String tempIntegral = String.valueOf(integral);
    while(tempIntegral.length()>3) {
      result = ","+tempIntegral.substring(tempIntegral.length()-3, tempIntegral.length())+result;
      tempIntegral = tempIntegral.substring(0,tempIntegral.length()-3);
    }
    result = tempIntegral+result;
    return result;
  }

  public static String insertCommaMoney(BigDecimal money) {
    /*** parn edit ***/
    if (money == null) {
      return "0.00";
    }
    /************/

    String strMoney = money.toString();
    String baht = "0";
    String satang = "00";

    if (strMoney.indexOf(".") != -1) {
      baht = strMoney.substring(0, strMoney.indexOf("."));
      satang = strMoney.substring(strMoney.indexOf(".") + 1);
    }
    else {
      baht = strMoney;
    }

    for (int i = 0; i < Math.floor((baht.length() - (1 + i))/3); i++) {
      baht = baht.substring(0, baht.length() - (4 * i + 3)) + ',' +
         baht.substring(baht.length() - (4 * i + 3));
    }

    return baht + "." + satang;
  }

  public static BigDecimal convertToBigDecimal(String str) {
    String bigDecimalText = str.replaceAll(",", "");
    return new BigDecimal(bigDecimalText);
  }

  public static String randomNumber(int digits) {
    String preMaxValue = "";
    for (int i = 0; i < digits; i++ ) {
      preMaxValue += "9";
    }
    int maxvalue = Integer.parseInt(preMaxValue);

    String random = new Long((Math.round(Math.random() * maxvalue))).toString();
    for (int i = random.length(); i < digits; i++ ) {
      random = "0" + random;
    }
    return random;
  }

  public static String readMoneyToText(String fullBaht) {

    String[] textNumber = {"", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า",
                           "หก", "เจ็ด", "แปด", "เก้า"};

    String[] textDigit = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน", "สิบ",
                          "ร้อย","พัน","หมื่น","แสน","ล้านล้าน"};

    String[] specialTextNumber = {"เอ็ด", "ยี่"};

    fullBaht = fullBaht.replaceAll(",","");
    String[] splitBahtText = fullBaht.split("\\.");
    String textBaht = "";

      String baht = splitBahtText[0];
      int bathDigit = baht.length();
      for (int i = 1; i <= bathDigit; i++) {
        int digit = Integer.parseInt(baht.substring(i - 1, i));
        int digitPos = bathDigit - i;

        switch (digit) {
          case 1:
            if ((bathDigit > 1) &&
                (i == bathDigit) ||
                (i == (bathDigit - 6)) &&
                (i != 1)) {
              if (baht.substring(i - 2, i - 1).equals("0")) {
                textBaht += textNumber[digit];
              }
              else {
                textBaht += specialTextNumber[digit - 1];
              }
            }
            else {
              if (!textDigit[digitPos].equals("สิบ")) {
                textBaht += textNumber[digit];
              }
            }
            textBaht += textDigit[digitPos];
            break;
          case 2:
            if (textDigit[digitPos].equals("สิบ")) {
              textBaht += specialTextNumber[digit - 1];
            }
            else {
              textBaht += textNumber[digit];
            }
            textBaht += textDigit[digitPos];
            break;
          default:
            if (digit != 0) {
              textBaht += textNumber[digit] + textDigit[digitPos];
            }
            if ((digit == 0) && (i == (bathDigit - 6))) {
              textBaht += "ล้าน";
            }
            break;
        }
      }

      if (!textBaht.equals("")) {
        textBaht += "บาท";
      }

      String textSatang = "";

      if (splitBahtText.length > 1) {
      String satang = splitBahtText[1];
      int satangDigit = satang.length();
      for (int i = 1; i <= satangDigit; i++) {
        int digit = Integer.parseInt(satang.substring(i - 1, i));
        int digitPos = satangDigit - i;

        switch (digit) {
          case 1:
            String firstSatang =
                satang.substring(satangDigit - 2, satangDigit - 1);
            if ((satangDigit > 1) && (i == satangDigit)) {
              if (firstSatang.equals("0")) {
                textSatang += textNumber[digit];
              }
              else {
                textSatang += specialTextNumber[digit - 1];
              }
            }
            else {
              if (!textDigit[digitPos].equals("สิบ")) {
                textSatang += textNumber[digit];
              }
            }
            textSatang += textDigit[digitPos];
            break;
          case 2:
            if (textDigit[digitPos].equals("สิบ")) {
              textSatang += specialTextNumber[digit - 1];
            }
            else {
              textSatang += textNumber[digit];
            }
            textSatang += textDigit[digitPos];
            break;
          default:
            if (digit != 0) {
              textSatang += textNumber[digit] + textDigit[digitPos];
            }
            break;
        }
      }
      if (!textSatang.equals("")) {
        textSatang += "สตางค์";
      }
    }
    if (textSatang.equals("")) {
      textBaht += "ถ้วน";
    }

    return textBaht + textSatang;
  }

  ////////////////////////////////////////////////////////////////////

  public static String toString(Integer s, Integer digit) {

    if(s == null | digit == null) throw new IllegalArgumentException("input is null.");

    NumberFormat nf=NumberFormat.getInstance(); // Get Instance of NumberFormat
    nf.setMinimumIntegerDigits(digit);  // The minimum Digits required is 5
    nf.setMaximumIntegerDigits(digit); // The maximum Digits required is 5
    nf.setParseIntegerOnly(true);
    String sb=(nf.format(s));
    sb=sb.replace(",", "");
    return sb;
  }


  public static boolean validateNidCheckDigit(String s) {
    return validateMoiCheckDigit(s) || validateMocCheckDigit(s);
  }

  public static boolean validateTinCheckDigit(String s) {
    return s.substring(9, 10).equals(getTinCheckDigit(s));

  }

  private static boolean validateMocCheckDigit(String s) {
    if (s.length() != NID_LENGTH)
      return false;
    else
      return s.substring(NID_LENGTH - 1, NID_LENGTH)
          .equals(getMocCheckDigit(s));
  }

  public static String getMocCheckDigit(String s) {
    int ai[] = getArrayOfIntFromSequenceDigitString(s);
    int i = ai[0] + ai[1] + ai[3] + ai[6] + ai[8];
    int j = ai[2] + ai[5] + ai[9];
    int k = ai[4] + ai[7] + ai[10];
    int l = (j <= k ? k : j) * 7;
    int i1 = ai[11] * 17;
    int j1 = (i + l + i1) % 10;
    return String.valueOf(j1);
  }

  public static boolean validateMoiCheckDigit(String s) {
    if (s.length() != PIN_LENGTH)
      return false;
    String s1;
    String s2;
    s1 = s.substring(PIN_LENGTH - 1, PIN_LENGTH);
    s2 = getMoiCheckDigit(s.substring(0, PIN_LENGTH - 1));
    return s1.equals(s2);

  }

  public static String getMoiCheckDigit(String s) {
    int i = 0;
    long l = Long.parseLong(s);
    for (int k = 2; k <= PIN_LENGTH; k++) {
      i = (int) ((long) i + (l % 10L) * (long) k);
      l /= 10L;
    }

    int j = (11 - i % 11) % 10;
    return String.valueOf(j);
  }

  public static String getTinCheckDigit(String s) {
    int ai[] = getArrayOfIntFromSequenceDigitString(s);
    int i = 3 * (ai[0] + ai[2] + ai[4] + ai[6] + ai[8])
        + (ai[1] + ai[3] + ai[5] + ai[7]);
    int j = 10 - i % 10;
    int k = j % 10;
    return String.valueOf(k);
  }

  private static int[] getArrayOfIntFromSequenceDigitString(String s) {
    int ai[] = new int[s.length()];
    for (int i = 0; i < s.length(); i++)
      ai[i] = Integer.parseInt(s.substring(i, i + 1));

    return ai;
  }

  public static String formatPartialUniversalId(long l, int i) {
    String s = String.valueOf(l);
    int j = s.length();
    if (j < i) {
      for (int k = 0; k < i - j; k++)
        s = "0" + s;

    }
    return s;
  }

  public static String getPartialTin(String s) {
    return s.substring(0, 9);
  }

  public static boolean isUniversalIdNumber(String s) {
    return isTinNumber(s) || isNationalIdNumber(s);
  }

  public static boolean isNationalIdNumber(String s) {
    return isRdNumber(s) || isMoiNumber(s) || isMocNumber(s);
  }

  public static boolean isTinNumber(String s) {
    return s != null && s.length() == 10 && isAllDigit(s)
        && validateTinCheckDigit(s);
  }

  public static boolean isMoiNumber(String s) {
    if (s != null && s.length() == PIN_LENGTH && isAllDigit(s)) {
      String s1 = s.substring(0, 3);
      int i = Integer.parseInt(s1);
      return 100 <= i && i <= 999 && validateMoiCheckDigit(s);
    }
    else {
      return false;
    }
  }

  public static boolean isMocNumber(String s) {
    if (s != null && s.length() == PIN_LENGTH && isAllDigit(s)) {
      int i = Integer.parseInt(s.substring(0, 3));
      return 10 <= i && i <= 96 && validateMoiCheckDigit(s);
    }
    else {
      return false;
    }
  }

  public static boolean isRdNumber(String s) {
    if (s != null && s.length() == PIN_LENGTH && isAllDigit(s)) {
      String s1 = s.substring(0, 3);
      int i = Integer.parseInt(s1);
      return i == 99 && validateMoiCheckDigit(s);
    }
    else {
      return false;
    }
  }

  public static boolean isUid(String s) {
    return s != null && s.length() == 25 && isAllDigit(s);
  }

  public static boolean isDummyPin(String s) {
    for (int i = 0; i < dummyPin.length; i++)
      if (s.equals(dummyPin[i]))
        return true;

    return false;
  }

  public static String formatPartialNationalId(long l) {
    return formatPartialUniversalId(l, 12);
  }

  public static String getNationalIdCheckDigit(String s) {
    return getMoiCheckDigit(s);
  }

  public static boolean validateUniversalCheckDigit(String s) {
    return validateTinCheckDigit(s) || validateNidCheckDigit(s);
  }

  public static int TIN_LENGTH = 10;

  public static int PIN_LENGTH = 13;

  public static int NID_LENGTH = 13;

  private static String dummyPin[] = { "1111111111119", "0000000000000" };

  public static List<Integer> convertInt2IntList(Integer value) {
    return convertString2IntList(value + "");
  }

  public static List<Integer> convertString2IntList(String str) {
    if (str != null) {
      str = str.replaceAll(" ", "");

      List<Integer> intList = new ArrayList<Integer>();

      if ( !str.contains(",") ) {
        if ( isAllDigit(str) ) {
          intList.add( new Integer(str) );
        }
      }
      else {
        String[] strList = str.split(",");
        for (String eachStr : strList) {
          if ( isAllDigit(eachStr) ) {
            intList.add( new Integer(eachStr) );
          }
        }
      }
      return (intList.size() == 0) ? null : intList;
    }
    return null;
  }

  public static List<Integer> convertIntObjArr2IntList(Integer[] intObjArr) {
    if (intObjArr != null) {
      List<Integer> intList = new ArrayList<Integer>();

      for (Integer eachInt : intObjArr) {
        intList.add(eachInt);
      }
      return intList;
    }
    return null;
  }

  public static int[] convertIntList2IntObjArr(List<Integer> intList) {
    if (intList != null) {
      int[] intArr = new int[intList.size()];
      for (int i = 0; i < intList.size(); i++) {
        intArr[i] = intList.get(i);
      }
      return intArr;
    }
    return null;
  }

  private static String dummyPTinZero[] = { "0", "0000000000" };
  public static boolean isTinZero(String s) {
    if (s != null) {
      for (int i = 0; i < dummyPTinZero.length; i++) {
        if (dummyPTinZero[i].equals(s.trim())) {
          return true;
        };
      }
      return false;
    }

    return false;
  }

}
