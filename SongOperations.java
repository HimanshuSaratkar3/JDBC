package com.hs.jdbctask;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SongOperations {
	static private Connection connection;
	static private PreparedStatement preparedStatement;
	static private ResultSet resultSet;
	private static Properties properties;
	static private int result;
	static private String filepath = "C:\\J2EE\\JDBC\\resource\\jd_info.properties";
	private static Scanner scanner = new Scanner(System.in);
	
	static private int id;
	static private String name;
	static private String singer;
	static private String movie;
	static private String liricist;
	static private String duration;
	
	private void openConnection() {
		try {
			FileReader fileReader = new FileReader(filepath);
			properties = new Properties();
			properties.load(fileReader);
			Class.forName(properties.getProperty("driverpath"));
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);		
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	private void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (scanner != null) {
			scanner.close();
		}

	}
	
	 void addSong()  {
		openConnection();
		try {
			preparedStatement = connection.prepareStatement(properties.getProperty("query1"));
			
			System.out.println("Enter the id : ");
			id = scanner.nextInt();
			preparedStatement.setInt(1, id);
			
			System.out.println("Enter the Name of the Song");
			name =scanner.next();
			preparedStatement.setString(2, name);
			
			System.out.println("Enter the Singer of the Song");
			singer =scanner.next();
			preparedStatement.setString(3, singer);
			
			System.out.println("Enter the Movie of the Song");
			movie =scanner.next();
			preparedStatement.setString(4, movie);
			
			System.out.println("Enter the Liriscist of the Song");
			liricist =scanner.next();
			preparedStatement.setString(5, liricist);
			
			System.out.println("Enter the  duration of the Song");
			duration =scanner.next();
			preparedStatement.setString(6, duration);
			
			result = preparedStatement.executeUpdate();
			System.out.println(result + "row(s) added");
			
			closeConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}
	 
	 public static void main(String[] args) {
		SongOperations operations = new SongOperations();
		operations.addSong();
	}


}
