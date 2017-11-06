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
	<%Integer permissions = (Integer)session.getAttribute("permissions");%>
	<c:if test="${permissions == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
	<c:if test="${permissions != 3}">
		<jsp:include page="menu.jsp"/>
	</c:if>
	
 
	<jsp:include page="menuF.jsp"/>

<div align="center">
	<h2> User Resources</h2>
	
	<c:if test="${empty files }">
					<div style="color:blue">No resources stored</div>
				</c:if>
				
				<div style="color:blue">${Msg}</div>
				
		<table class="table table-striped">
			<thead>
			<TR>
				<TH>Name</TH>
				<TH>Size</TH>
				<TH>Type</TH>
			</TR>
			</thead>
			<tbody>
    			<c:forEach items="${files}" var="file">
          		<tr>
					<td> <a href ="/download?idFile=${file.idFile}" >${file.oriName}</a> </td>
					<td> <c:out value="${file.size}"/> MB</td>
					<td> <c:out value="${file.type}" /></td>
					<td>
						<a href = "/deleteResource?idFile=${file.idFile}"><img src="resources/img/iconDeletematerial.png"/></a>
					</td>
   			   </tr>
   			   </c:forEach>
 			</tbody>
 		</table>
 	</div>
 	
 	<jsp:include page="end.jsp"/>

</body>
 
</html>