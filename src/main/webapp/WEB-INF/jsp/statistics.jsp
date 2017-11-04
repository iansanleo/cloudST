<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>

	<jsp:include page="head.jsp"/>
	
    <link rel="stylesheet" href="/resources/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/jquery.circliful.css">

	<title>Statistics</title>

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
              <a class="nav-link active" href="/statistics">Overview <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/statFiles">Storage/Files</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/statUser">Users </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Export</a>
            </li>
          </ul>

          <ul class="nav nav-pills flex-column">
            <li class="nav-item">
              <a class="nav-link" href="/userList">List of Users</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/devicesList">List of devices</a>
            </li>
          </ul>
        </nav>


        <main role="main" style="width:80%">
          <h1 align=center>Dashboard</h1>
          	<br>
           <div class="row">
        		<div class="col-lg-2">
           		 	<div id="test-circle"></div>
        		</div>
        		<div class="col-lg-2">
           		 	<div id="test-circle1"></div>
        		</div>
        		<div class="col-lg-2">
            		<div id="test-circle6"></div>
       			</div>
    </div> 
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.circliful.js"></script>
<script>
$( document ).ready(function() { // 6,32 5,38 2,34
    $("#test-circle").circliful({
        animationStep: 5,
        foregroundBorderWidth: 5,
        backgroundBorderWidth: 15,
        percent: ${usedPercent},
        icon: 'f0a0',
        iconPosition: 'middle',
        text: 'Used Space',
        textBelow: true
    });
    $("#test-circle1").circliful({
        animationStep: 5,
        foregroundBorderWidth: 5,
        backgroundBorderWidth: 15,
        percent: ${percentNoValid},
        icon: 'f0a0',
        iconPosition: 'middle',
        text: 'No valid Users',
        textBelow: true
    });
    $("#test-circle6").circliful({
        animation: 1,
        animationStep: 5,
        foregroundBorderWidth: 7,
        backgroundBorderWidth: 7,
        textSize: 28,
        textStyle: 'font-size: 12px;',
        textColor: '#666',
        multiPercentage: 1,
        percentages: [
            {'percent': ${percentAdminUser}, 'color': '#FFCA35', 'title': 'Admin User' },
            {'percent': ${percentPaidUser}, 'color': '#4ADBEA', 'title': 'Paid User' },
            {'percent': ${percentFreeUser}, 'color': '#49EBA8', 'title': 'Free User' },
        ],
        multiPercentageLegend: 1,
        replacePercentageByText: '',
        backgroundColor: '#eee',
        icon: 'f0d0',
        iconPosition: 'middle',
        iconColor: '#273B4E'
    });
});
</script>
 </main>
      </div>
    </div>
  </body>
</html>