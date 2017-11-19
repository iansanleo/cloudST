<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Welcome to CloudST</title>
    <jsp:include page="head.jsp"></jsp:include>	

</head>
<body>

	<div id="header">
		<div class="header_top">
			<div class="header_top_right"></div>
		</div>
	</div>

	<div id="login" align="left" style="margin: 0 auto; margin-top: 10%">
		<form action="login" method="post">

      	   Username: <br/>
      	   <input type="text" name="userName" value="christian"/><br/>

     	    Password :<br/>
     	    <input type="password" name="password" value="123456"/><br/>

			<div style="color:red">${Msg}</div>
     	   <input type="submit" value="Access!"/>
     	 </form>
     	 <div id="url">
     	 	<a href="/forgot">Forgot your password? </a><br/>
     	 	Not a member yet?
     	 	<a href="/newUser">Create a account! </a><br>
     	 	
     	 	
		</div>
	</div>
	<jsp:include page="end.jsp"/>
</body>
</html>