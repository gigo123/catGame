package com.thsoft.catgame.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlWorker {
	public boolean isHasConection() {
		return hasConection;
	}

	private Connection conn;
	private Statement st;
	private boolean hasConection;
	private static final String CREATE = "CREATE TABLE IF NOT EXISTS  users"
			+ " (Id INTEGER PRIMARY KEY AUTOINCREMENT, login VARCHAR(30), password VARCHAR(60),  autolofin BOOL , score INT)";
	private static final String USERFIND = "SELECT * FROM  users WHERE login = ?";
	private static final String INSERTUSER = "INSERT INTO  users" + "(login,password,autolofin,score) VALUES (?,?,?,?)";
	// private static final String SELECT = "SELECT * FROM dog WHERE id = ?";

	public SqlWorker() {
		super();

		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			System.out.println("loading my sql driver");
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
			st = conn.createStatement();
			hasConection = true;
			
			ResultSet rs;
			rs = st.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");

			// Command to show tables in databases
			// System.out.println("counting");
			while (rs.next()) {
				System.out.println(rs.getString(1));
				if (rs.getString(1).equals("users")) {
					return;
				}
			}
			rs.close();

			PreparedStatement ps;
			ps = conn.prepareStatement(CREATE);
			ps.executeUpdate();
			System.out.println("tabel added");
		} catch (Exception ex) {
			hasConection = false;
			System.out.println("failed");
		//	ex.printStackTrace();
		}
	}

	
	public boolean userExist(String login) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(USERFIND);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while (rs.next()) {
			//	System.out.println(rs.getString(1));
			//	System.out.println(rs.getString(2));
			//	System.out.println(rs.getString(3));
				return true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			hasConection = false;
		}
		return false;
	}
	
	public String returnPass(String login) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(USERFIND);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(3);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			hasConection = false;
		}
		return null;
	}

	public void addUser(User user) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(INSERTUSER);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setBoolean(3, user.isAutologin());
			ps.setInt(4, user.getScore());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error");
			hasConection = false;
		}

	}
	public void close() {
		try {
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
