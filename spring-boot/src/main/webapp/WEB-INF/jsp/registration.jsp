<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta charset="utf-8">
    <title>Registration form</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/users" method="POST">
    <div class="form-group">
        <label for="usernameForm">User name</label>
        <input type="text" class="form-control" id="usernameForm" placeholder="Choose username" name="username"
               required>
    </div>
    <div class="form-group">
        <label for="passwordForm">Password</label>
        <input type="text" class="form-control" id="passwordForm" placeholder="Choose password" name="password"
               required>
    </div>
    <div class="form-group">
        <label for="confirmPasswordForm">Confirm password</label>
        <input type="text" class="form-control" id="confirmPasswordForm" placeholder="Confirm password" name="confirmPassword"
               required>
    </div>
    <div class="form-group">
        <label for="roleForm">Role select</label>
        <select datatype="text" class="form-control" id="roleForm" name="role" required>
            <option value="CUSTOMER">CUSTOMER</option>
            <option value="ADMIN">ADMIN</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
