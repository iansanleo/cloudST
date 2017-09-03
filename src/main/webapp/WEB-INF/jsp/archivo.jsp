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
	<jsp:include page="menu.jsp"></jsp:include>

<div align="center">
	<h2> User Resources</h2>
		<table style="border: 1px solid #000;text-align:center ;border: 1px solid #000;border-collapse: separate;
  border-spacing: 10px;">
			
				<c:if test="${empty archivos }">
					<div style="color:blue">No resources stored</div>
				</c:if>
				
				<div style="color:blue">${Msg}</div>
			
			<TR style="color:black;background-color:white;">
			<TH>Name</TH>
			<TH>Size</TH>
			<TH>Type</TH>
			<TR>
    			<c:forEach items="${archivos}" var="archivo">
          			  <tr>
					<td> <c:out value="${archivo.tamanyo}"/> MB</td>
					<td> <c:out value="${archivo.tipo}" /></td>
					<td>
					<a href = "/deleteResource?idArchivo=${archivo.idArchivo}"><img src="resources/img/iconDeletematerial.png"/></a></td>
   			   </c:forEach>

 			</tr>
 		</table><BR>
 	</div>
</body>
 
</html>