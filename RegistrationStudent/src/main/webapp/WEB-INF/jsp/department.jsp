<%@ page import="java.lang.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<h2>Create department</h2>
<script type="text/javascript">
	function logExit() {
		document.deptForm.action = "department.html?method=logOut";
		document.deptForm.submit();
	}

	function createDepartment() {
		document.deptForm.action = "department.html?method=createDepartment";
		document.deptForm.submit();
	}
	
	function editDepartmentJsp(id){
		window.location.href="department.html?method=displayUpdateDepartment&id="+id;
	}
	
	function updateDepartment() {
		document.deptForm.action = "department.html?method=updateDepartment";
		document.deptForm.submit();
	}
	
	function deleteDepartment(id) {
		window.location.href = "department.html?method=deleteDepartment&id="+id;
	}
	
	
</script>
</head>

<body>
	<form:form name="deptForm" method="post" commandName="departmentVo">
		<table>
			<tr>
				<td><label>Department Name</label></td>
				<td><form:input path="name" /></td>
				<td><form:hidden path="id"/></td>
			</tr>
			
			<c:if test="${(departmentVo.create == true)}">
				<tr>
					<td><input id="deptCreate" type="button" name="deptCreate"
						value="Create Department" onclick="createDepartment();" /></td>
				</tr>
			</c:if>
			
			<c:if test="${(departmentVo.create == false)}">
				<tr>
					<td><input id="deptUpdate" type="button" name="deptUpdate"
						value="Update Department" onclick="updateDepartment();" /></td>
				</tr>
			</c:if>
			<tr>
				<td><input type="button" name="logOut" value="Log Out"
					onclick="logExit();" /></td>
			</tr>
		</table>
		<table>
			<c:if test="${not empty errorDept}">
				<tr>
					<td><c:out value="${errorDept}"></c:out></td>
				</tr>
			</c:if>
			<c:if test="${not empty message}">
				<tr>
					<td><c:out value="${message}"></c:out></td>
				</tr>
			</c:if>
		</table>
	</form:form>
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Department Name</td>
		</tr>
		<c:forEach var="dept" items="${deptList}">
			<tr>
				<td><c:out value="${dept.id}" /></td>
				<td><c:out value="${dept.name}" /></td>
				<td><input type="button" name="EditDept" value="Edit"
					onclick="editDepartmentJsp(${dept.id});" /></td>
				<td><input type="button" name="DeleteDept" value="Delete"
					onclick="deleteDepartment(${dept.id});" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>