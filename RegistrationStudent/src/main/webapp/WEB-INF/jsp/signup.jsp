<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<h2>SIGN UP PAGE</h2>
<script  type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">
function userNameExists() {
	 $.ajax({
		type:"post",
		url:"student.html?method=userNameExists",
		cache:false,
		data:'userName=' + $("#userName").val(),
		success:function(response){
			$("#userNameExistMessage").text(response);
		},
	    error:function(){      
		   alert('Error while request..');
	    }
	}); 
}

function rollNumberExists() {
	$.ajax({
		type:"post",
		url:"student.html?method=rollNumberExists",
		cache:false,
		data:'rollNo=' + $("#rollNo").val(),
		success:function(response){
				$("#rollNoExistMessage").text(response);
		},
		error:function(){      
		   alert('Error while request..');
	    }
	});
}

</script>
</head>
<body>
	<form:form name="signupform" method="post"
		action="student.html?method=doSignUp" commandName="studentVo">
		<table cellpadding="1" cellspacing="1" align="center">
			<tr>
				<td><label>UserName:</label></td>
				<td><form:input path="userName" onblur="userNameExists();"/></td>
				<td><form:errors path="userName"/></td>
				<td><label id="userNameExistMessage"/></td>
			</tr>

			<tr>
				<td><label>Password:</label></td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" /></td>
			</tr>
			<tr>
				<td><label>Roll Number</label></td>
				<td><form:input path="rollNo"  onblur="rollNumberExists();"/></td>
				<td><label id="rollNoExistMessage"/></td>
			</tr>
			<tr>
				<td><label>FirstName</label></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><label>LastName</label></td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td><label>Address</label></td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td><label>City</label></td>
				<td><form:input path="city" /></td>
			</tr>
			<tr>
				<td><label>Age</label></td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td><label>Department</label></td>
				<td>
							
				<form:select path="department">
				 <form:option value="NONE" label=" Select " />
 				   <form:options items="${deptNameList}" />
				</form:select>
				</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" name="signupsubmit" value="submit" /></td>
			</tr>
		</table>

	</form:form>
</body>