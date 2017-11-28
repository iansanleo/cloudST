<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>New Password</title>

</head>

<body>
<h2 align="center" style="color:#ec6d1e; align-text:center; margin: 0 auto; margin-top: 5%">Write your new password here</h2>
	
	<div class="form" align="center" style="margin: 0 auto; margin-top: 10%">
		<div style="color:red">${Msg}</div>
		<form action="reset?token=${resetToken}" method="post">
			 Password:  <input type="password" name="password"/><br/>
			 Confirm:  <input type="password" name="password2"/><br/>
			 <input type="submit" value="Reset!"/><br/>
		</form>
</div>

</body>
</html>