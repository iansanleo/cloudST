<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Upload a File</title> 

</head>

<body>
	<jsp:include page="menu.jsp"/>
	
	<%Integer permisos = (Integer)session.getAttribute("permisos");%>
	<c:if test="${permisos == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
 
	<jsp:include page="menuF.jsp"/>
	
	
	<h2 align="center">Upload your files</h2> 
	  
	<div class="form">

		<div style="color:red">${Msg}</div>
		
		<form method="POST" action="/uploadFile" enctype="multipart/form-data">
   		 <input type="file" name="file" /><br/><br/>
    		<input type="submit" value="Submit" />
    		
		</form>
	</div>

</body>
</html>