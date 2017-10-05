<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
	<jsp:include page="head.jsp"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/jquery.circliful.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="/resources/js/jquery.circliful.js"></script>
	
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
              <a class="nav-link active" href="#">Overview <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Reports</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Analytics</a>
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


		
        <main class="col-sm-9 ml-sm-auto col-md-10 pt-3" role="main">
          <h1>Dashboard</h1>
		
				<section class="container">
			<div class="row">
        		<div class="col-lg-2">
           		 	<div id="test-circle5"></div>
        		</div>
    		</div>
		</section>
		
          <section class="row text-center placeholders">
          	
            <div class="col-6 col-sm-3 placeholder">
              	<div id="test-circle5"></div>
              	<h4>Used Space</h4>
             	<div class="text-muted">Used</div>
            </div>
            
            <div class="col-6 col-sm-3 placeholder">
              <img src="data:image/gif;base64,R0lGODlhAQABAIABAADcgwAAACwAAAAAAQABAAACAkQBADs=" width="200" height="200" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-6 col-sm-3 placeholder">
              <img src="data:image/gif;base64,R0lGODlhAQABAIABAAJ12AAAACwAAAAAAQABAAACAkQBADs=" width="200" height="200" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-6 col-sm-3 placeholder">
              <img src="data:image/gif;base64,R0lGODlhAQABAIABAADcgwAAACwAAAAAAQABAAACAkQBADs=" width="200" height="200" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
          </section>

          <h2>Section title</h2>
          
          
        </main>
      </div>
    </div>

<script>
$( document ).ready(function() { // 6,32 5,38 2,34
    $("#test-circle").circliful({
        animation: 1,
        animationStep: 5,
        foregroundBorderWidth: 15,
        backgroundBorderWidth: 15,
        percent: 38,
        textSize: 28,
        textStyle: 'font-size: 12px;',
        textColor: '#666',
    });
    $("#test-circle2").circliful({
        animation: 0,
        animationStep: 6,
        foregroundBorderWidth: 5,
        backgroundColor: "none",
        fillColor: '#eee',
        percent: 50,
        iconColor: '#3498DB',
        icon: 'f206',
        iconSize: '40',
        iconPosition: 'middle'
    });
    $("#test-circle3").circliful({
        animation: 1,
        animationStep: 6,
        foregroundBorderWidth: 5,
        backgroundBorderWidth: 1,
        percent: 88,
        iconColor: '#3498DB',
        icon: 'f004',
        iconSize: '40',
        iconPosition: 'middle'
    });
    $("#test-circle4").circliful({
        animation: 1,
        animationStep: 1,
        target: 10,
        start: 2,
        showPercent: 1,
        backgroundColor: '#000',
        foregroundColor: '#A8C64A',
        fontColor: '#000',
        iconColor: '#000',
        icon: 'f0a0',
        iconSize: '40',
        iconPosition: 'middle',
        text: 'No Kids'
    });
    $("#test-circle5").circliful({
        animationStep: 5,
        foregroundBorderWidth: 5,
        backgroundBorderWidth: 15,
        percent: 80,
        icon: 'f0a0',
        iconPosition: 'middle',
        text: 'Space Left',
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
            {'percent': 10, 'color': '#3180B8', 'title': 'Gryffindor' },
            {'percent': 30, 'color': '#4ADBEA', 'title': 'Ravenclaw' },
            {'percent': 50, 'color': '#49EBA8', 'title': 'Hufflepuff' },
            {'percent': 70, 'color': '#FFCA35', 'title': 'Slytherin' }
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

  <jsp:include page="end.jsp"/>
  </body>
</html>