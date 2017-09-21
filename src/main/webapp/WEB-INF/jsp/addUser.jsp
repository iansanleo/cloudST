<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Add User</title>

</head>

<body>
	
	<div class="form">
		<form action="userAdd" method="post">

			<div style="color:red">${Msg}</div>
			
      	    Username:        <input type="text" name="userName"/><br/>
     	    Password:        <input type="password" name="password"/><br/>
     	    Repeat Password: <input type="password" name="password2"/><br/>     	    
     	    Complete Name:   <input type="text" name="name"/><br/>
     	    Email:           <input type="text" name="email"/><br/>
     	   
     	   <input type="submit" value="Add User"/><br/>
     	 </form>
			<a href="/"> Back </a>
	</div>

</body>
</html>