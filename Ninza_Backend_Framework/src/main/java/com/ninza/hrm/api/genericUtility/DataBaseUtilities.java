package com.ninza.hrm.api.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

// FOR JDBC program we need to write methods withing try-catch block as there're chances of SQL exception if there's any syntax errors
public class DataBaseUtilities {

	static FileUtility fLib = new FileUtility();
	static Connection con;

	public void getDBConnection() throws Throwable {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection(fLib.getDataFromPropertiesFile("DB_URL"),
					fLib.getDataFromPropertiesFile("DB_Username"), fLib.getDataFromPropertiesFile("DB_Password"));
		} catch (Exception e) {
			System.out.println("===Connection Done===");
		}
	}

	public void closeDbConnection() throws Throwable {
		try {
			con.close();
		} catch (Exception e) {
		}
	}

	public ResultSet executeSelectQuery(String query) throws Throwable {
		ResultSet result = null;
		try {
			Statement stat = con.createStatement();
			result = stat.executeQuery(query);

		} catch (Exception e) {
		}
		return result;/// return in the form of table
	}

	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement stat = con.createStatement();
			result = stat.executeUpdate(query);
		} catch (Exception e) {
		}
		return result;/// return in the form +-int
	}

	public boolean executeQueryVerifyAndGetData(String query, int coloumnIndex, String expectedData) throws Throwable {
		boolean flag = false;
		ResultSet resultset = con.createStatement().executeQuery(query);
		while (resultset.next()) {
			if (resultset.getString(coloumnIndex).equals(expectedData)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println(expectedData + "====>data verified in data base table");
			return true;
		} else {
			System.out.println(expectedData + "====>data not verified in data base table");
			return false;
		}
	}
}
