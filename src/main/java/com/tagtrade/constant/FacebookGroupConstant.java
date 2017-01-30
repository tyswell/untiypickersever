package com.tagtrade.constant;

public class FacebookGroupConstant {
	
	
	public static final int CODE_ONE = 1;
	public static final String DESC_ONE = "ซื้อขายจักรยานและอุปกรณ์จักรยานมือ 1 มือ 2";
	
	public static final int CODE_TWO = 2;
	public static final String DESC_TWO = "ซื้อ-ขาย อุปกรณ์จักรยาน Enduro - FR - DH";
	
	public static final int CODE_THREE = 3;
	public static final String DESC_THREE = "ชมรม ศิษย์หลวงพ่อโบ้ย";
	
	public static String codeToDesc(int code) {
		if (code == CODE_ONE) {
			return DESC_ONE;
		} else if (code == CODE_TWO) {
			return DESC_TWO;
		} else if (code == CODE_THREE) {
			return DESC_THREE;
		} 
		return null;
	}
	

}
