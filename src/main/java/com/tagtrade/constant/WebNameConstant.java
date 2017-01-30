package com.tagtrade.constant;

public class WebNameConstant {
	
	public static final int THAIMTB_CODE = 1;
	public static final String THAIMTB_DESC = "THAIMTB";
	
	public static final int FACEBOOK_CODE = 2;
	public static final String FACEBOOK_DESC = "FACEBOOK_CODE";
	
	public static final int WEBPRA_CODE =3;
	public static final String WEBPRA_DESC = "WEB-PRA";
	
	public static String codeToDesc(int code) {
		if (code == THAIMTB_CODE) {
			return THAIMTB_DESC;
		} else if (code == FACEBOOK_CODE) {
			return FACEBOOK_DESC;
		} else if (code == WEBPRA_CODE) {
			return WEBPRA_DESC;
		} 
		return null;
	}
	
}
