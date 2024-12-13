package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class JavaUtility {
	
	public int getRandomNumber()
	{
		Random random=new Random();
		int random_no = random.nextInt(2000);
		
		return random_no;
	}
	
	public String getSystemdate()   //formate yyyy-MM-dd
	{
		Date dateObj = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		
		return date;
				
	}
	
	public String getRequiredDate(int days)  //formate yyyy-MM-dd after 30 days
	{
		Date dateObj = new Date();
		SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd");
		dateFormate.format(dateObj);

		Calendar cal = dateFormate.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = dateFormate.format(cal.getTime());
		
		return reqDate;
	}
	
	public String getRequiredDataByYear(int years)
	{
		Date dateObj = new Date();
		SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd");
		dateFormate.format(dateObj);

		Calendar cal = dateFormate.getCalendar();
		cal.add(Calendar.DAY_OF_YEAR, years);
		String reqDate = dateFormate.format(cal.getTime());
		
		return reqDate;
	}
	
	public void wait(int x) throws Throwable
	{
		Thread.sleep(x);
	}
}
