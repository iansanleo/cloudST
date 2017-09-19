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
 
 
	<div class="form">
	 	 <h1 style="aligng:center">Edit user information</h1>
	 
   
    <c:if test="${ empty idUser}"> 
      <form action="userEdit" method="post"> 
     
       </c:if> 
       <c:if test="${not empty idUser}"> 
            <form action="userEdit?idUser=${idUser}" method="post">  
       </c:if> 
           
		
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
     	    
     	     <c:if test="${idUser != null}"> 
     	     <tr>
     	        <td><b>Username</b></td> 
             	<td><input type="text" name="username" value="${usuario.username}"/></td> 
     	    </tr>
     	    <tr>
     	        <td><b>Email Valid</b></td> 
                <td><input type="text" name="valido" value="${usuario.valido}"/></td> 
     	    </tr>
     	    </c:if> 
            
           </table> <br><br> 
           <c:if test="${idUser == null}"> 
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
           </c:if> 
            
          <input type="submit" value="Edit"/>  
          <c:if test="${empty idUser }"> 
              <a href="/user">Go Back</a>  
          </c:if> 
            <c:if test="${not empty idUser}"> 
              <a href="/userList">Go Back</a>  
          </c:if> 
     	 </form>

		
	</div>
</body>
</html>