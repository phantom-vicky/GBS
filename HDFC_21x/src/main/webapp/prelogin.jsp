<%@page language="java"	import="java.io.*,java.net.InetAddress,java.util.*,java.sql.*"%>
<%@page import = "java.io.*,java.util.*, jakarta.servlet.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<% 
    String sErrorMessage=""; 
    if( session.getAttribute ( "xLoginMessage" ) != null ) 
        sErrorMessage=(String)session.getAttribute("xLoginMessage"); 
    else if (session.getAttribute("xMessage") != null) 
    	sErrorMessage=(String)session.getAttribute("xMessage"); 
    session.invalidate(); 
%>
<html>

<script language="javascript">
	document.domain = 'accelfrontline.com';
</script>
<script language="javascript"> 
<% 
/****************************************************************************************** 
Name	: login 
Purpose	: Login the user by passing the values,to the servlet 
*****************************************************************************************/ 
%> 

function login(serviceToken, userID, sess, langCode) 
{ 
	document.frmPreLogin.SESSION_ID.value = sess; 
	document.frmPreLogin.SERVICE_TOKEN.value = serviceToken; 
	document.frmPreLogin.USER_ID.value = userID; 
	document.frmPreLogin.LCLANG.value = langCode; 
	document.frmPreLogin.COMMAND.value = "LOGIN"; 
	document.frmPreLogin.LANG.value="EN"; 
	document.frmPreLogin.submit(); 
} 
<% 
/****************************************************************************************** 
Name		:	getLoggedStatus 
Purpose		:	Returns the logged in status of the current user. 
Parameters	:	None 
Returns		:  	0 
Dependency	: 	No 
*****************************************************************************************/ 
%> 
function getLoggedStatus() 
{ 
	return 0; 
} 
 
var appStartTime = new Date(); 
<% 
/**************************************************************************** 
Name		:	getIdleTime 
Purpose		:	Obtains the idle time of the user in the session 
Parameters	:	Date 
Returns		:  	diffTime 
Dependency	: 	No 
*****************************************************************************/ 
%> 
function getIdleTime() { 
    currTime = new Date(); 
    diffTime = Date.parse(currTime.toString()) - Date.parse(appStartTime.toString()); 
    return diffTime; 
} 
 

</script>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<c:set var="context" value="<%=request.getContextPath()%>"/>
<body>
	<table width="40%" border="0" cellspacing="0" cellpadding="8">
		<form name='frmPreLogin' method='post' action='${context}/gbmultra/login'>
			<input type="hidden" name="SESSION_ID" value="" /> 
			<input type="hidden" name="SERVICE_TOKEN" value="" /> 
			<input type="hidden" name="USER_ID" value="" /> 
			<input type="hidden" name="LCLANG" value="" /> 
			<input type="hidden" name="COMMAND" value="" /> 
			<input type="hidden" name="LANG" value="" /> 
			<input type="hidden" name="MENU" value="" />
		</form>
	</table>
</body>
</html>
