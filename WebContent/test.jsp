<%@page language="java"
	import="java.util.*,java.sql.*,javax.naming.*,javax.sql.*"
	pageEncoding="GB2312"%>
<%@page import="com.mchange.v2.c3p0.*"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'mysqlTest.jsp' starting page</title>
</head>

<body>
	MysqlÊı¾İ¿â²âÊÔ
	<br>
	<br>
	<br>
	<%
		Connection conn = null;
		try {
			InitialContext ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/sign");
			conn = ds.getConnection();
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

		String sql = "select * from teacher";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
	%>
	×Ö¶Î1£º<%=rs.getString(1)%>
	×Ö¶Î2£º<%=rs.getString(2)%><br>
	<%
		}
		if (rs != null) {
			rs.close();
			rs = null;
		}
		if (ps != null) {
			ps.close();
			ps = null;
		}
		if (conn != null) {
			conn.close();
			conn = null;
		}
	%>
</body>
</html>