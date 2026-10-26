<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">	
    <title>Government Business Suite</title>
    <c:set var="context" value="<%=request.getContextPath()%>"  scope="session"/>
    <c:set var="userID" value="${userId}" scope="session"/>
    <c:set var="solDt" value="${solDate}" scope="session"/>
    <!-- Bootstrap -->
    <link href="${context}/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet/less" type="text/css" href="${context}/assets/css/style.css" />
   <link href="${context}/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
  </head>
  <body>
	<div class="header_top">
		<div class="container">
			<div class="col-sm-2">
				<img class="img-responsive">
			</div>
			<div class="col-sm-8">
				<h3>Government Business Suite</h3>
			</div>
			<div class="col-sm-2">
				<img src="${context}/assets/images/axis_logo.png" class="img-responsive" alt="axis">
			</div>
		</div>
	</div>
	
		<nav class="navbar navbar-default">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" data-target="#navbarCollapse" data-bs-toggle="collapse" class="navbar-toggle">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					
				</div>
				
			</div>
		</nav>	
	 <div id="page-wrapper">
	 	<div class="container-fluid">
			<div class="row">
				<h1 class="welcome">${errorMessage}</h1>
			</div>
			<!-- /.row -->
		
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.container-fluid -->
     </div>
    <script src="${context}/assets/js/jquery.min.js"></script>
    <script src="${context}/assets/js/bootstrap.min.js"></script>
	<script src="${context}/assets/js/less.min.js" type="text/javascript"></script>
	<script src="${context}/assets/js/common.js" type="text/javascript"></script>
  </body>
</html>