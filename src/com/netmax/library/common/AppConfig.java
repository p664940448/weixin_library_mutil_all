package com.netmax.library.common;

import com.jfinal.kit.PropKit;

public class AppConfig {
    
	/**
	' allow_num 一个人允许预约的书的数量
	' yue_eff_days 预约有效天数
	' book_allow_num 一本书允许预约的人数
	*/
	
//	public static final int EFF_DAYS=5; //预借有效天数
//	public static final int Book_Allow_Num=5; //一本书允许预约的人数	
//	public static final int ALLOW_NUM=10; //一个人允许预约的书的数量
//	public static final int YUE_EFF_DAYS=7; //预约有效天数
	
	public static final int EFF_DAYS = Integer.parseInt(PropKit.get("EFF_DAYS")); //预借有效天数
	public static final int Book_Allow_Num = Integer.parseInt(PropKit.get("Book_Allow_Num")); //一本书允许预约的人数	
	public static final int ALLOW_NUM = Integer.parseInt(PropKit.get("ALLOW_NUM")); //一个人允许预约的书的数量
	public static final int YUE_EFF_DAYS = Integer.parseInt(PropKit.get("YUE_EFF_DAYS")); //预约有效天数
	

	
}
