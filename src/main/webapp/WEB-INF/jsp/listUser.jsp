<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
 
<jsp:include page="head.jsp"></jsp:include>	
 
<title>Resources</title>

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

      <div class="container-fluid">
      <div class="row">
        <nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
          <ul class="nav nav-pills flex-column">
            <li class="nav-item">
              <a class="nav-link" href="/statistics">Overview </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/statFiles">Storage/Files</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/statUser">Users</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Export</a>
            </li>
          </ul>

          <ul class="nav nav-pills flex-column">
            <li class="nav-item">
              <a class="nav-link active" href="/userList">List of Users <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/devicesList">List of Devices</a>
            </li>
          </ul>
          </nav>  
		
        <main class="col-sm-9 ml-sm-auto col-md-10 pt-3" role="main">
          <h2 style="color:black;align-text:center; margin: 0 auto; margin-top: 5%" align="center">List Users</h2>
			<div class="table-responsive" align="center">
        	<table class="table table-striped">
        	<thead>
				<TR>
					<TH>UserName</TH>
					<TH>Complete Name</TH>
					<TH>Email</TH>
					<TH>Date </TH>
					<TH>Email Validated</TH>
					<TH>Status</TH>
					<TH>Last login</TH>
					<TH>Status</TH>
				</TR>
				</thead>
				<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td> ${user.username} </td>
						<td> ${user.name} </td>
						<td> ${user.email}  </td>
						<td> ${user.dateCreated}</td>
						<td> ${user.valid}  </td>
						<td> ${user.status}</td>
						<td> ${user.lastLogin} </td>
						<td>
							<a href = "/editUser?idUser=${user.idUser}"><img src="resources/img/iconEditmaterial.png"/></a>
							<a href = "/delAdminUser?idUser=${user.idUser}" onclick="return confirma()"><img src="resources/img/iconDeletematerial.png"/></a>
						</td>
 					</tr>
 				</c:forEach>
 				</tbody>
 			</table>
 			</div>
 		</main>
 		</div>
		</div>
 	

</body>
 
</html>