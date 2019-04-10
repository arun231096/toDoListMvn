<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Edit TODO</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
  	h3 {
  		color: red;
  	}
  </style>
</head>
<body>

	<div class="col-sm-4" style="padding:40px;">
	<c:if test="${error != null }"> <h3>${error}</h3> </c:if>
	<%
	    if (request.getParameter("error") != null) {
	        out.println("<h3>"+request. getParameter("error")+"</h3>");
	    }
	%>
		<h2>Your TODO</h2>
		<form action="update" method="post">
			<input type="hidden" value="${ id }" name="id">
			<div class="form-group">
			<label>Title</label>
			<input type="text" value="${ title }" name="title" class="form-control" required>
			</div>
			<div class="form-group">
			<label>Message</label>
			<input type="text" value="${ message }" name="message" class="form-control" required>
			</div>
			<div class="form-group">
			<label>Estimation</label>
			<input type="text" value="${ estimation }" name="estimation" class="form-control" required>
			</div>
			<div class="form-group">
			<label>Status</label>
			<input type="text" value="${ status }" name="status" class="form-control" required>
			</div>
			<div class="form-group">
			<label>Start Date</label>
			<input type="date" value="${ startdate }" name="startdate" class="form-control"
			pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
			required>
			</div>
			<div class="form-group">
			<label>Due Date</label>
			<input type="date" value="${ duedate }" name="duedate" class="form-control"
			pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
			required>
			</div>
			<div class="form-group">
			<input type="submit" value="Update" class="btn btn-primary">
			</div>
		</form>
	</div>
</body>
</html>
