package org.house.predict.config;
import java.util.*;
import java.sql.*;
import java.io.*;
public class DBConfig {
	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;
    private static DBConfig db=null;
    private DBConfig()  {
		
		try{
		
			Properties p=new Properties();
			p.load(PathHealper.finf);
			String driverclassname=p.getProperty("driver.classname");
			String username=p.getProperty("db.username");
			String passward=p.getProperty("db.passward");
			String url=p.getProperty("db.url");
		    //Class.forName(driverclassname);
			com.mysql.cj.jdbc.Driver d = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(d);
			conn = DriverManager.getConnection(url,username,passward);
		    if(conn!=null)System.out.println("Database is connceted....");
		    else System.out.println("Error database is not connected...");
			
		}
		catch(Exception ex) {
			System.out.println("Error is:"+ex);
		}
	}
	public static DBConfig getInstance() {
		if(db==null) {
			db=new DBConfig();
		}
		return db;
	}
public static Connection getConnection() {
	return conn;
}
public static PreparedStatement getStatement() {
	return stmt;
}
public static ResultSet getResult() {
	return rs;
}
}
