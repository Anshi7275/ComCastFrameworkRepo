package com.comcast.practice.generic.practice;

import java.sql.ResultSet;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;

public class DatabaseTest {

	public static void main(String[] args) throws Throwable {
		
		DataBaseUtility dfil=new DataBaseUtility();
		
		dfil.getDBconection();
		
		ResultSet data = dfil.executeConnSelectQuery("select * from student;");
		
		dfil.closeDBConnection();
		
		System.out.println(data);

	}

}
