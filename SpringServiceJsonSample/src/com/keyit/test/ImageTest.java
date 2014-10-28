package com.keyit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageTest {
	
	private static Connection conn;
	
	static {		
		String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/keyit_motivedriverestaurant_001";
        String user = "root";
        String password = "root";
        try {
			Class.forName(driver);		
       
			conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	

	public static void main(String[] args) throws SQLException, IOException {
		File image = new File("C:\\Users\\Stephen\\Desktop\\chicken.jpg");
		InputStream fis = new FileInputStream(image);
		
		PreparedStatement preparedStatement = conn
				.prepareStatement("update tbl_restaurant set image=? where restaurantID=1");
		// Parameters start with 1		
		preparedStatement.setBinaryStream(1, fis, (int)image.length());
		
		preparedStatement.executeUpdate();
		fis.close();
		
		System.out.println("Done...");

	}

}
