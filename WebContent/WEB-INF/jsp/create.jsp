<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Create ToDo</title>
  <link rel="stylesheet" href="resources/css/style.css">
  <script src="resources/js/script.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="col-sm-4" style="padding:20px;">
    <c:if test="${error != null }"> <h3>${error}</h3> </c:if>
        <h2>Create Your TODO</h2>
        <form action="/ToDoList/create" method="post">
            <div class="form-group">
            <label>Title</label>
            <input type="text" name="title" class="form-control" required>
            </div>
            <div class="form-group">
            <label>Message</label>
            <input type="text" name="message" class="form-control" required>
            </div>
            <div class="form-group">
            <label>Estimation</label>
            <input type="text" name="estimation" class="form-control" required>
            </div>
            <div class="form-group">
            <label>Status</label>
            <input type="text" name="status" class="form-control" required>
            </div>
            <div class="form-group">
            <label>Start Date</label>
            <input type="date" name="startdate" class="form-control" 
            pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
            required>
            </div>
            <div class="form-group">
            <label>Due Date</label>
            <input type="date" name="duedate" class="form-control" 
            pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
            required>
            </div>
            <div class="form-group">
            <input type="submit" value="Create" class="btn btn-primary">
            </div>
        </form>
    </div>
</body>
</html>