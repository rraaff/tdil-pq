package com.tdil.milka.utils.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import com.tdil.ibatis.IBatisManager;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.MilkaPhotoExample;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;

public class MilkaTest {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, ParseException {
		createDataInDatabase("jdbc:mysql://localhost:3306/MILKA", "MILKA_USER", "MILKA_USER");
	}
	
	public static void createDataInDatabase(String connectionURL, String dbUser, String dbPassword) throws SQLException, IOException {
		Properties p = new Properties(); 
		p.setProperty("connectionURL", connectionURL);
		p.setProperty("username", dbUser);  
		p.setProperty("password", dbPassword);
			  
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml", p);
		IBatisManager.getClient().startTransaction();
		MilkaPhotoExample mpe = new MilkaPhotoExample();
		List<MilkaPhotoValueObject> list = DAOManager.getMilkaPhotoDAO().selectMilkaPhotoToApproveWithAuthor();
		System.out.println(list);
		IBatisManager.getClient().commitTransaction();
	}
}
