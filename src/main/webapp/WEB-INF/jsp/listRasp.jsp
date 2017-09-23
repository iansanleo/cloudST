<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Devices Conected</title>

</head>

<body>
	<jsp:include page="menu.jsp"/>
	
	<%Integer permissions = (Integer)session.getAttribute("permissions");%>
	<c:if test="${permissions == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
 
	<jsp:include page="menuF.jsp"/>
	
<div align="center">
	<h2> Devices</h2>
	<div style="color:red">${Msg}</div>
		<table style="border: 1px solid #000;text-align:center ;border: 1px solid #000;border-collapse: separate; border-spacing: 10px;">
			<TR style="color:black;background-color:white;">
			<TH>IP</TH>
			<TH>MAC</TH>
			<TH>totalSize</TH>
			<TH>useSize </TH>
			<TH>conexionDate</TH>

			</TR>
			<c:forEach items="${devices}" var="dev">
				<tr>
					<td> ${dev.ip} </td>
					<td> ${dev.mac} </td>
					<td> ${dev.totalSize}  </td>
					<td> ${dev.useSize}</td>
					<td> ${dev.conexionDate}  </td>

					<td>
					<a href = "#"><img src="resources/img/iconEditmaterial.png"/></a>
					<a href = "/deleteDevice?deviceId=${dev.idRaspberry}"><img src="resources/img/iconDeletematerial.png"/></a></td>
				
 				</tr>
 			</c:forEach>
 		</table>
 	</div>
</body>
 
</html>