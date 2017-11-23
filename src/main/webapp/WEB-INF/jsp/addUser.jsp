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
	<h2 align="center" style="color:#ec6d1e; align-text:center; margin: 0 auto; margin-top: 5%">Be a new member of CloudST!</h2>
	<div class="form" align="left" style="margin-left: 30%; margin-top=20%;">
		<form action="userAdd" method="post">
			
			<div style="color:red">${Msg}</div>
			
      	    Username:       <br/> <input type="text" name="userName"/><br/>
     	    Password:       <br/> <input type="password" name="password"/><br/>
     	    Repeat Password:<br/> <input type="password" name="password2"/><br/>     	    
     	    Complete Name:  <br/> <input type="text" name="name"/><br/>
     	    Email:          <br/> <input type="text" name="email"/><br/>
     	   
     	   <input type="submit" value="Add User"/><br/>
     	 </form>
			<a href="/"> Back </a>
	</div>

</body>
</html>