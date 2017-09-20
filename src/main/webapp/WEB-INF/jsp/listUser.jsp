<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Resources</title>

</head>

<body>
	<jsp:include page="menu.jsp"/>
	
	<%Integer permissions = (Integer)session.getAttribute("permissions");%>
	<c:if test="${permissions == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
 
	<jsp:include page="menuF.jsp"/>

<div align="center">
	<h2> List Users</h2>
		<table style="border: 1px solid #000;text-align:center ;border: 1px solid #000;border-collapse: separate; border-spacing: 10px;">
			<TR style="color:black;background-color:white;">
			<TH>UserName</TH>
			<TH>Complete Name</TH>
			<TH>Email</TH>
			<TH>Date </TH>
			<TH>Email Validated</TH>
			<TH>Status</TH>
			<TH>Last login</TH>
			<TH>Status</TH>
			</TR>
			<c:forEach items="${users}" var="user">
				<tr>
					<td> ${user.username} </td>
					<td> ${user.name} </td>
					<td> ${user.email}  </td>
					<td> ${user.dateCreated}</td>
					<td> ${user.valid}  </td>
					<td> ${user.status}</td>
					<td> ${user.lastLogin} </td>
					<td>
					<a href = "/editUser?idUser=${user.idUser}"><img src="resources/img/iconEditmaterial.png"/></a>
					<a href = "/delAdminUser?idUser=${user.idUser}"><img src="resources/img/iconDeletematerial.png"/></a></td>
				
 				</tr>
 			</c:forEach>
 		</table>

 	</div>
</body>
 
</html>