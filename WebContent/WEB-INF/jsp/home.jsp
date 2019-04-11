<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
	<title>Home</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <script src="resources/js/script.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="col-sm-12" style="padding:20px;">
		<h1>Welcome to ToDo!</h1>
	<c:if test="${not empty lists}">
	<div class="container" style="padding-bottom:15px;">
			<div class="col-sm-4"></div>
			<div class="col-sm-4"></div>
			<div class="col-sm-4" align="right">
			   <a href="todo/create">
			       <button class="btn btn-info">Add ToDo</button>
		       </a>
			</div>
	</div>
	<%
		int count =1;
	%>
		<table border="1" class="table table-striped">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Estimation</th>
				<th>Start Date</th>
				<th>Due Date</th>
				<th>Message</th>
				<th>Status</th>
				<th>Edit Progress</th>
				<th>Remove Progress</th>
			</tr>
			<c:forEach var="listValue" items="${lists}">
				<tr>
					<td><% out.println(count); count++; %></td>
					<td>${listValue.title}</td>
					<td>${listValue.estimation}</td>
					<td>${listValue.startdate}</td>
					<td>${listValue.duedate}</td>
					<td>${listValue.messgae}</td>
					<td>${listValue.status}</td>
					<td><form action="read">
							<input type="hidden" value="${listValue.id}" name="id" id="id" class="id"/>
							<input type="submit" value="Edit" class="btn btn-primary center-block">
						</form>
					</td>
					<td><form action="delete">
							<input type="hidden" value="${listValue.id}" name="id" id="id" class="id"/>
							<input type="submit" value="Delete" class="btn btn-danger center-block">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
</body>
</html>
