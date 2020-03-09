<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta charset="utf-8">
    <title>Login form</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="POST">
    <c:if test="${param.error != null}">
        <div>
            <h2 style="color: firebrick">
                Failed to login.
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                </c:if></h2>
        </div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div>
            <h2 style="color: #2eb22b">
                You have been logged out.</h2>
        </div>
    </c:if>

    <div class="form-group">
        <label for="inputUsername">Username</label>
        <input type="text" class="form-control" id="inputUsername" aria-describedby="usernameHelp" name="username"
               required>
        <small id="usernameHelp" class="form-text text-muted">We'll never share your login with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="inputPassword">Password</label>
        <input type="password" class="form-control" id="inputPassword" name="password" required>
    </div>
    <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="rememberMe" name="remember-me">
        <label class="form-check-label" for="rememberMe">Remember me</label>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<hr/>
</body>
</html>
