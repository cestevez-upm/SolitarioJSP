<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Error</title>
</head>
<body>
	<h1>Error</h1>
	<sql:setDataSource driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/jee" user="root" password="" />
	<sql:query var="logs">SELECT * FROM log</sql:query>
	<table border="1px">
		<caption>Logs:</caption>
		<c:forEach var="row" items="${logs.rows}">
			<tr>
				<td><c:out value="${row.id}" /></td>
				<td><c:out value="${row.prioridad}" /></td>
				<td><c:out value="${row.fecha}" /></td>
				<td><c:out value="${row.clase}" /></td>
				<td><c:out value="${row.mensaje}" /></td>
			</tr>
		</c:forEach>
	</table>
	<p><a href="?action=home">Home</a></p>
</body>
</html>