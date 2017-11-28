<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Forgot Mail</title>

</head>

<body>
		<h2 align="center" style="color:#ec6d1e; align-text:center; margin: 0 auto; margin-top: 5%">Have you forgotten your password? <br/>
		Insert your email here and we will send you an email to restart it.</h2>
		
	<div class="form"align="center" style="margin: 0 auto; margin-top: 10%">
		<div style="color:red">${Msg}</div>
		<form action="forgot" method="post">
			 Email:  <input type="text" name="userEmail"/><br/>
			 <input type="submit" value="Enter"/><br/>
		</form>
</div>
</body>
</html>