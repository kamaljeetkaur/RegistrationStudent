<%@page import="java.util.*,java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<h3>Student List</h3>
<script type="text/javascript">
function displayUpdateStudent(id){
	window.location.href="student.html?method=displayUpdateStudent&id="+id;
}

function deleteStudent(id){
	window.location.href="student.html?method=deleteStudent&id="+id;
}

</script>
</head>
<body>
	<table border ="1">
		<tr>
			<td>Roll No</td>
			<td>User Name</td>
			<td>Name</td>
			<td>Address</td>
			<td>City</td>
			<td>Age</td>
			<td>Department</td>
		</tr>

		<c:forEach var="student" items="${studentList}">
			<tr>
				<td><c:out value="${student.rollNo}" /></td>
			 	<td><c:out value="${student.userName}"/></td>
				<td><c:out value="${student.firstName} ${student.lastName}" /></td>
				<td><c:out value="${student.address}"/></td>
				<td><c:out value="${student.city}"/></td>
				<td><c:out value="${student.age}"/></td> 
				<td><c:out value = "${student.department.name}"/></td>
				<td><input type="button" value="Edit" name="Edit" onclick="displayUpdateStudent(${student.id});"/></td>
				<td></td>
				<td><input type="button" value="Delete" name="Delete" onclick="deleteStudent(${student.id});"/></td>
			</tr>
		</c:forEach>
	</table>
		<table><tr>
			<td><c:out value = "${message}"/></td></tr>
			</table>
</body>
</html>
