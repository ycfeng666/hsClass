package com.inspur.ch6;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatDemo {
	/**
	 * ������ת��Ϊ�ַ���
	 * @param date
	 * @return
	 */
   public static String dateToString(Date date){
	   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   return simpleDateFormat.format(date);
   }
}
