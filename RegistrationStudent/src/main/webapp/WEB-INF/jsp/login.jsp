<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<h2>LOGIN PAGE</h2>
<script type="text/javascript">
function doLogin(){
	document.loginForm.action="login.html?method=doLogin";
	document.loginForm.submit();
}

function doSignUp(){
	document.loginForm.action="login.html?method=displaySignUp";
	document.loginForm.submit();
}

function displayDept(){
	document.loginForm.action="login.html?method=displayDepartment";
	document.loginForm.submit();
}
</script>
</head>
<body>
<form:form name = "loginForm" method="POST" commandName="loginUserVo">
		<table cellsapcing="0" cellpadding="0" border="0" align="center">
 				<tr>
 					<td><label>UserName:</label></td>
 					<td><form:input  path="userName"/></td>
 					<td><form:errors path="userName"/></td>
 				</tr>
 
				 <tr>
 					<td><label>Password:</label></td>
 					<td><form:password path="password" /></td>
 					<td><form:errors path="password"/></td>
 				</tr>
 	
 				<tr>
 					<td></td><td><input type="button" name="Submit" value="Login" onclick="doLogin();" />
 				</tr>
 				
 				<tr>
 					<td></td><td><input type="button" name="signup" value="Sign Up" onclick="doSignUp();" />
 				</tr>
 				
 				<tr>
 					<td></td><td><input type="button" name="createDepartment" value="Create Department" onclick="displayDept();" />
 				</tr>
 				</table>
 				<table>
 				 <tr><td>
 					<c:out value ='${Error}'/>
			 	</td></tr> 
 				
 				</table>



</form:form>
</body>
</html>