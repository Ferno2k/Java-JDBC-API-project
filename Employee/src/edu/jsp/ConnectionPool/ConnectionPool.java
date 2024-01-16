package edu.jsp.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

	private static final int POOL_SIZE = 5;
	private static String dburl = "jdbc:postgresql://localhost:5432/jdbc";
	private static String user = "postgres";
	private static String password = "root";
	
	private static List<Connection> connectionPool = new ArrayList<>();
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			
			for(int i = 0; i < POOL_SIZE;i++) {
				connectionPool.add(createConnection());
			}
//			System.out.println("connection added: " + connectionPool.size());;
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(!connectionPool.isEmpty()) {
//			System.out.println("getconnection: " + (connectionPool.size()-1));
			return connectionPool.remove(0);
		}else {			
			return createConnection();
		}
	}
	
	public static Connection createConnection() {
		
		Connection connection;
		
		try {
			 connection = DriverManager.getConnection(dburl, user, password);
			return connection;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void receiveConnection(Connection connection) {
		if(connectionPool.size() < POOL_SIZE) {
			connectionPool.add(connection);
//			System.out.println("connection recived. remeaning connections: " + connectionPool.size());
		}else {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
