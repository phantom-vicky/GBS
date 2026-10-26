<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
  <head>
  	<c:set var="context" value="<%=request.getContextPath()%>"/>
  	<c:set var="solId" value="${solId}" />
    <c:set var="userID" value="${userId}" />
    <c:set var="solDt" value="${solDate}" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>GBM Ultra</title>
    <link href="${context}/assets/css/home-style.css" rel="stylesheet">
   <link href="${context}/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  </head>
  	<script>
		function setCookie() {
		    var d = new Date();
		   	document.cookie = "username=${userId};path=/"; 
		   	document.cookie = "solId=${solId};path=/"; 
		}
	</script>
  <body onload="setCookie()">
	<div class="header_top">
		<div class="container">
			<div class="accel-logo">
				<img class="img-responsive" >
			</div>
			<div class="main-heading">
				<h3>Government Business Suite</h3>
			</div>
			<div class="bank-logo">
				<img src="${context}/assets/images/axis_logo.png" class="img-responsive" alt="axis">
			</div>
		</div>
	</div>
	<div style="clear:both;"></div>
		<div class="navbar-default">
			<div class="container">
					<div class="space">.</div>
					<div class="version">Version : Ultra 1.0 &nbsp;&nbsp;&nbsp;<span><b>|</b><span></div>
					<div class="admin"><a href="#" style="color: #c2cae8;"> <i class="fa fa-user"></i> &nbsp;<b>${userId} ${solDate}</b></a></div>
			</div>
		</div>
	<div class="container">
		<div class="welcome">
				<h1 class="welcome">Welcome to GBM Ultra !</h1>
				<h1 class="welcome"><a href="${context}/#!/" target="_blank">Data Center</a></h1>
		</div>
	</div>
  </body>
</html>