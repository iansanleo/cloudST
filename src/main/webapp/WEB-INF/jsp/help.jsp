<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Help</title>

</head>

<body>
<%Integer permissions = (Integer)session.getAttribute("permissions");%>
	<c:if test="${permissions == 3}">
		<jsp:include page="menuA.jsp"/>
	</c:if>
	<c:if test="${permissions != 3}">
		<jsp:include page="menu.jsp"/>
	</c:if>
	
 
	<jsp:include page="menuF.jsp"/>
	
		<h2 align="center" style="color:#ec6d1e; align-text:center; margin: 0 auto; margin-top: 5%">Have you forgotten your password? <br/>
		F.A.Q</h2>
		
	<div class="form"align="center" style="margin: 0 auto; margin-top: 10%">
		<ul>
			<li><h3> Why can not I upload any file?</h3>
			</li>
			<p>To upload any file first check that you have validated your email and that you have 
			not exceeded the storage limit of your account. <br/>
			If everything is correct check that your file does not weigh more than 2 MB.</p>
			<br/>
			<li><h3> How can I validate my email? </h3>
			</li>
			<p>To validate your email you can look in the tray of your email or in the spam folder.
			<br/> If you want to receive the email again, go to your Profile and click on the envelope that appears.
			<br/>Once validated, a green check will appear in the place of the envelope in your Profile.</p>
			<br/>
			<li><h3> How can I increase the limit storage of my account?</h3>
			</li>
			<p>To do this contact the administrator, he will give you more information.</p>
			<br/>
			<li><h3> I have changed my email and now I can not upload files</h3>
			<p>If you have modified the data corresponding to your email you have to re-validate the account you have entered,
			 for this search in the tray of the entered email or in the spam folder.</p>
			</li>
		</ul>
</div>
</body>
</html>