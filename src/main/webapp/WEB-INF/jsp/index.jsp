<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome to the System For Booking Room</title>
    <link type="text/css" rel="stylesheet" href="src/main/webapp/WEB-INF/css/style.css"/>


</head>
<body>

	<div id="header">
		<div class="header_top">
			<div class="header_top_right"></div>
		</div>
	</div>

	<div id="login" align="center" style="margin: 0 auto; margin-top: 10%">
		<form action="login" method="post">

      	   Username: <input type="text" name="userName" value="christiansl"/><br/>

     	    Password : <input type="password" name="password" value="123"/><br/>

     	   <input type="submit" value="/login"/>
     	 </form>
     	 <div id="url">
     	   <a href="#">I forgot my password </a>
		</div>
	</div>
</body>
</html>