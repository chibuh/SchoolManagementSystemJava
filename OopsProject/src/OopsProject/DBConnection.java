package OopsProject;
import java.util.*;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection{
	final String DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/smssql";
	final String DB_USER = "root";
	final String DB_PASSWORD = "";
	Connection connection;
	
	public DBConnection() {
		connect();
	}
	
	public void connect() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}
		catch(SQLException|ClassNotFoundException e) {
			System.out.println("Error establishing connection with database");
			System.out.println(e);
		}
	}
	
	public boolean insert(String query) {
		try {
			Statement statement = connection.createStatement();
			return statement.execute(query);
		}
		catch(SQLException e) {
			System.out.println("Error in executing INSERT statement");
			System.out.println(e);
			return false;
		}
	}
	
	public ResultSet select(String query) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}
		catch(SQLException e) {
			System.out.println("Error in executing SELECT query");
			System.out.println(e);
			return null;
		}
	}
	
	public int update(String query) {
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		}
		catch(SQLException e) {
			System.out.println("Error in executing UPDATE query");
			System.out.println(e);
			return -1;
		}
	}
	
	public boolean delete(String query)
	{
		try {
			Statement statement = connection.createStatement();
			if(statement.executeUpdate(query)!=0)
			{
				return true;
			}
			else
				return false;
		}
		catch(SQLException e) {
			System.out.println("Error in executing DELETE query");
			System.out.println(e);
			return false;
		}
	}
	
	public void close(){
	    try{
	      connection.close();
	    }
	    catch(SQLException e){
	      System.out.println("ERROR while closing connections!");
	      System.out.println(e.toString());
	    }
	  }
}