<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<title>constitution groupes</title></head>
<body>

<sql:setDataSource var="co" driver="com.mysql.jdbc.Driver"
  url="jdbc:mysql://localhost:3306/gnou"
  user="root"  password="root"/>
 
<sql:query dataSource="${co}" var="result">
		SELECT * FROM profil 
	</sql:query>

</body>
</html>