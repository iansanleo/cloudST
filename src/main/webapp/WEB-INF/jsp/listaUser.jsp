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
	
	<%Integer permisos = (Integer)session.getAttribute("permisos");%>
	<c:if test="${permisos == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
 
	<jsp:include page="menuF.jsp"/>

<div align="center">
	<h2> List Users</h2>
		<table style="border: 1px solid #000;text-align:center ;border: 1px solid #000;border-collapse: separate; border-spacing: 10px;">
			<TR style="color:black;background-color:white;">
			<TH>UserName</TH>
			<TH>Password</TH>
			<TH>Complete Name</TH>
			<TH>Email</TH>
			<TH>Date </TH>
			<TH>Email Validated</TH>
			</TR>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td> ${usuario.username} </td>
					<td> ************* </td>
					<td> ${usuario.nombre} </td>
					<td> ${usuario.email}  </td>
					<td> ${usuario.fechaInicio}</td>
					<td> ${usuario.valido}  </td>
					<td>
					<a href = "/editAdminUser?idUser=${usuario.idUsuario}"><img src="resources/img/iconEditmaterial.png"/></a>
					<a href = "/delAdminUser?idUser=${usuario.idUsuario}"><img src="resources/img/iconDeletematerial.png"/></a></td>
				
 				</tr>
 			</c:forEach>
 		</table>

 	</div>
</body>
 
</html>