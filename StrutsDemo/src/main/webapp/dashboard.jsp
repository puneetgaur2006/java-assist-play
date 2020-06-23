<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ page import="com.acc.form.EmployeeForm"%>
<%@ page import="java.util.List"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management</title>
</head>

<body>

	<div class="col-sm-6">
		<h2>
			Manage <b>Employees</b>
		</h2>
	</div>
	<div class="col-sm-6">
		<a href="#addEmployeeModal" class="btn btn-success"
			data-toggle="modal"><span>Add New Employee</span></a>
	</div>
	<table id="example" class="table table-striped table-bordered"
		style="width: 100%">

		<thead align="center">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Dept</th>
				<th>Address</th>
				<th>Email</th>
				<th>Phone No.</th>
				<th>Update?</th>
				<th>Delete?</th>
			</tr>
		</thead>
		<tbody>
			<logic:iterate id="listId" name="list">
				<tr>
					<td><bean:write name="listId" property="id" /></td>
					<td><bean:write name="listId" property="name" /></td>
					<td><bean:write name="listId" property="dept" /></td>
					<td><bean:write name="listId" property="address" /></td>
					<td><bean:write name="listId" property="email" /></td>
					<td><bean:write name="listId" property="phone" /></td>
					<td><button type="button" class="btn btn-primary">Update</button></td>
					<td><button type="button" class="btn btn-success">Delete</button></td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>

	<%-- <html:link action="/addEmployee">Add Employee</html:link>
	<br></br>
	<html:link action="/updateEmployee">Update Employee</html:link> --%>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>


</body>
</html>