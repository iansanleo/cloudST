<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Welcome</title>

</head>
<body>
	<%Integer permissions = (Integer)session.getAttribute("permissions");%>
	<c:if test="${permissions == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
	<c:if test="${permissions != 3}">
		<jsp:include page="menu.jsp"/>
	</c:if>
	
 
	<jsp:include page="menuF.jsp"/>
	
	<div class="form">
		<h1 align="center" style="color:black;align-text:center; margin: 0 auto; margin-top: 10%"> WELCOME </h1><br>
		<h2>${Msg}</h2>
	</div>
	<jsp:include page="end.jsp"/>
</body>
</html>