package edu.joseph.controler.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBase {

	private static Connection connection = null;

	private static Properties loadProperties() {

		try (var fs = new FileInputStream(
				"C:\\Users\\josea\\OneDrive\\Documentos\\WorkSpace java\\library\\src\\main\\resources\\application.properties")) {
			var properties = new Properties();
			properties.load(fs);
			return properties;
		} catch (IOException e) {
			throw new DbException("Error: " + e.getMessage());
		}
	}

	public static Connection getConnection() {

		try {
			if (connection == null) {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection(url, properties);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DbException("Error: " + e.getMessage());
			}
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
