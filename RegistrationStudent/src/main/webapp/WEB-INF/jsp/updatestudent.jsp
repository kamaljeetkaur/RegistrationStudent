<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<h2>EDIT STUDENT</h2>
<script type="text/javascript">
	function editStudent() {
		document.updateStudentForm.action = "student.html?method=updateStudent";
		document.updateStudentForm.submit();
	}
</script>
</head>
<body>
	<form:form name="updateStudentForm" commandName="studentVo"
		method="post">
		<table>
		<tr>
				<td><form:hidden path="id" /></td>
			</tr>
			<tr>
				<td><label>User Name</label></td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><label>First Name</label></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><label>Last Name</label></td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td><label>Roll No</label></td>
				<td><form:input path="rollNo" /></td>
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
				<td><form:select path="department">
						<form:option value="NONE" label=" Select " />
						<form:options items="${deptNameList}" />
					</form:select></td>
			</tr>
			<tr>
				<td><input type="button" name="update" value="update"
					onclick="editStudent();" /></td>
			</tr>
			
		</table>
<table><tr><td><c:out value="${message}"/></td></tr></table>

	</form:form>
	
	
</body>
</html>