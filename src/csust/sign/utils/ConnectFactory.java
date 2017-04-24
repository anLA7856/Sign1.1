package csust.sign.utils;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectFactory {

	private static InitialContext ctx = null;
	private static Context envContext = null;
	private static DataSource ds = null;


	
	



	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			if(ctx == null){
				ctx = new InitialContext();
			}
			if(envContext == null){
				envContext = (Context) ctx.lookup("java:/comp/env");
			}
			//ctx = new InitialContext();
			if(ds == null){
				ds = (DataSource) envContext.lookup("jdbc/sign");
			}
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return conn;
	}

	/**
	 * 关闭数据库连接，仍然血药关闭连接！，相当于放回数据库连接池，本线程不占用了。
	 * 
	 * @param pstam
	 * @param rs
	 * @param conn
	 */
	public static void close(PreparedStatement pstam, ResultSet rs,
			Connection conn) {
		if (pstam != null) {
			try {
				pstam.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
