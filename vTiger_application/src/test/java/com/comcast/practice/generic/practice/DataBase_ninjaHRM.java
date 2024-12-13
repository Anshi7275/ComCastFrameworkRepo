package com.comcast.practice.generic.practice;

import java.sql.ResultSet;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;

public class DataBase_ninjaHRM {

	public static void main(String[] args) throws Throwable {
		
		DataBaseUtility dfil= new DataBaseUtility();
		dfil.getDBconectionWithNinja();
		ResultSet data = dfil.executeConnSelectQuery("select * from project;");
		
		while(data.next())
		{
			System.out.println(data.getString(1));
		}
		dfil.closeDBConnection();
		
		

	}

}
