package com.emsi.Maven.jdbc.dao.Imp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				System.err.println("Problème de chargement de Driver Manager"+e);
			}
		}

		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			System.err.println("Erreur de fermeture de connexion");
			}
		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("src/main/resources/jdbc.ressources/db.properties")) {
			Properties props = new Properties();

			props.load(fs);

			return props;
		} catch (IOException e) {
			System.err.println("Erreur de chargement de proriétés");
		}
		return null;
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.err.println("Erreur de fermeture de Statement");
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("Erreur de fermeture de ResultSet");
			}
		}
	}
}
