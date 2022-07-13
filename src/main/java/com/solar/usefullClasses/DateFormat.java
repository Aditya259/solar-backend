package com.solar.usefullClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	
	public static String DateFormat(Date date) {
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		String strDate = formatter.format(date);
	    return strDate;
		}catch (Exception e) {
			return date.toString();
		}
	}
	
	public static String DateFormat2(Date date) {
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
		String strDate = formatter.format(date);
	    return strDate;
		}catch (Exception e) {
			return date.toString();
		}
	}
	
	public static String DateFormat3(Date date) {
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
		String strDate = formatter.format(date);
	    return strDate;
		}catch (Exception e) {
			return date.toString();
		}
	}
	
	public static String StringToDate(Date date) {
		try {
	         return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}catch (Exception e) {
			return null;
		}
	}

}
