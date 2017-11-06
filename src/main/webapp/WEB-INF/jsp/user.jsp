<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>User Profile</title>

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
	
	<div align="center">

	<h2> User Profile</h2>
	<div style="color:red">${Msg}</div>
	<br>
		<table class="table table-striped">
			<TR style="color:black;background-color:white;">
			<TH>UserName</TH>
			<TH>Password</TH>
			<TH>Complete Name</TH>
			<TH>Email</TH>
			<TH>Date </TH>
			<TH>Email Validated</TH>
			<TH>Last login </TH>
			<TR>
					<tr>
					<td> ${user.username} </td>
					<td> ************* </td>
					<td> ${user.name} </td>
					<td> ${user.email}  </td>
					<td> ${user.dateCreated} </td>
					<td>  
							<c:if test="${user.valid==false}">
								<a href ="/valid?idUser=${user.idUser}"><img src="resources/img/iconmail.png"/></a>
							</c:if>
							<c:if test="${user.valid}">
								<img src="resources/img/iconcheck.png"/>
							</c:if>
					</td>
					<td> ${user.lastLogin}  </td>
					<td>
					<a href = "/editUser"><img src="resources/img/iconEditmaterial.png"/></a>
					</tr>
 		</table><BR>
 	</div>
 	<jsp:include page="end.jsp"/>

</body>
 
</html>