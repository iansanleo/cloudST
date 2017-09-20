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
	<jsp:include page="menu.jsp"/>
	
	<%Integer permissions = (Integer)session.getAttribute("permissions");%>
	<c:if test="${permissions == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
 
	<jsp:include page="menuF.jsp"/>
	
<div align="center">
	<h2> User Profile</h2>
	<div style="color:red">${Msg}</div>
		<table style="border: 1px solid #000;text-align:center ;border: 1px solid #000;border-collapse: separate;
  border-spacing: 10px;">
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
					<td> ${user.dateCreated}</td>
					<td> ${user.valid}  </td>
					<td> ${user.lastLogin}  </td>
					<td>
					<a href = "/editUser"><img src="resources/img/iconEditmaterial.png"/></a>
					<a href = "#"><img src="resources/img/iconDeletematerial.png"/></a></td>

 			</tr>
 		</table><BR>
 	</div>
</body>
 
</html>