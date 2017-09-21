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
	
	<div class="form">
		<div style="color:red">${Msg}</div>
		<form action="reset?token=${resetToken}" method="post">
			 Password:  <input type="text" name="password"/><br/>
			 Confirm:  <input type="text" name="password2"/><br/>
			 <input type="submit" value="Reset!"/><br/>
		</form>
</div>

</body>
</html>