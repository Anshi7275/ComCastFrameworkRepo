package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.comcast.crm.generic.fileutility.IPathConstant;
import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {

	Connection conn;

	public void getDBconnection(String url, String username, String password) // connection
	{
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
		}
	}

	public void getDBconection() // hardcore the db url
	{
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(IPathConstant.db_url, IPathConstant.DB_UN, IPathConstant.DB_PWD); // crm app

		} catch (Exception e) {
		}
	}

	public void getDBconectionWithNinja() // hardcore the db url
	{
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection(IPathConstant.DB_URL, IPathConstant.DB_UN, IPathConstant.DB_PWD);

		} catch (Exception e) {
		}
	}

	public void closeDBConnection() // close the connection
	{
		try {

			conn.close();

		} catch (Exception e) {
		}
	}

	public ResultSet executeConnSelectQuery(String query) // execute select query
	{
		ResultSet result = null;
		try {

			Statement state = conn.createStatement();
			result = state.executeQuery(query);

		} catch (Exception e) {
		}

		return result;
	}

	public int executeConnNonSelectQuery(String query) // execute non select query
	{
		int result = 0;

		try {

			Statement state = conn.createStatement();
			result = state.executeUpdate(query);

		} catch (Exception e) {
		}

		return result;
	}

}
