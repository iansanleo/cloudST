<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html lang="en">
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Upload a File</title> 

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
	
	
	<h2 align="center">Upload your files</h2> 
	  
	<div class="form" align="center" style="margin: 0 auto; margin-top: 10%">

		<div style="color:red">${Msg}</div>
		
		<form method="POST" action="/uploadFile" enctype="multipart/form-data">
   		 
   		 	<input type="file" name="file" /><br/><br/>
    		<input type="submit" value="Upload!" />

		</form>
	</div>
	<jsp:include page="end.jsp"/>
</body>
</html>