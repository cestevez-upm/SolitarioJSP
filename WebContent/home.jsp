<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="CACHE-CONTROL" content="NO-CACHE" />
<meta http-equiv="Expires" content="-1" />
<title>Solitario</title>
</head>
<body>
	<h1>Solitario</h1>
	<h2>Home</h2>
	<c:set var="usr" scope="session" value="${user}" />
	<c:if test="${usr !=null }">
		<p>Bienvenido : ${usr.firstName}</p>
	</c:if>
	<p>
		<a href="?action=login">Login</a>
	</p>
	<p>
		<a href="?action=register">Register</a>
	</p>
</body>
</html>