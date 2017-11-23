<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Welcome to CloudST</title>
    <link rel="stylesheet" href="/resources/css/styleIndex.css">

</head>
<body>
<div class="wrapper">
	<div class="container">
		<h1>Welcome</h1>
		<div style="color:red">${Msg}</div>
		<form class="form" action="login" method="post">
			<input type="text" placeholder="Username" name="userName">
			<input type="password" placeholder="Password" name="password">
			<button type="submit" >Login</button>
		
		<div id="url">
     	 	<a href="/forgot">Forgot your password? </a><br/>
     	 	Not a member yet?
     	 	<a href="/newUser">Create a account! </a><br>
     	</form>
     	 	
		</div>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
 	
		</div>
	</div>
</body>
</html>