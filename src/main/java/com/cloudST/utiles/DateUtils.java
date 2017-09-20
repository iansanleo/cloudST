package com.cloudST.utiles;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtils {

	public static Date actualDate(){
		Date date = new java.util.Date();
		Timestamp sqlTimestamp = new Timestamp(date.getTime());//en milisegundos
		date = new Date(sqlTimestamp.getTime());
		
		return date;
	}
}
