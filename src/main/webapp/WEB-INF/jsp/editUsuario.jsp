<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Edit User</title>

</head>

<body>
	
	<jsp:include page="menu.jsp"/>
	
	<%Integer permisos = (Integer)session.getAttribute("permisos");%>
	<c:if test="${permisos == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
 
	<jsp:include page="menuF.jsp"/>
 
 
	 <h1  align="center">Edit your user information</h1>
	<div class="form">
		<form action="userEdit" method="post">
		
			<div style="color:red">${Msg}</div>
			
			
			<table>  	    
     	    <tr>
     	    	<td><b>Complete Name:</b></td>
     	    	<td> <input type="text" name="name" value="${usuario.nombre}"/></td>
     	    </tr>
     	    <tr>
     	    	<td><b>Email:</b></td>
     	    	<td><input type="text" name="email" value="${usuario.email}"/></td>
     	    </tr>
     	    </table> <br><br>
     	    	For a new Password:<br>
     	    <table>
     	    <tr>
     	    	<td><b>Password:</b></td>
     	    	<td><input type="password" name="password"/></td>
     	    	<td></td>
     	    </tr>
     	    <tr>
     	    	<td><b>Repeat Password:</b></td> 
     	    	<td><input type="password" name="password2"/></td>
     	    	<td></td>
     	    </tr>
     	    </table>
     	   <input type="submit" value="Edit"/> <a href="/user"> Go Back </a>
     	 </form>

		
	</div>
</body>
</html>