<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/users/add" method="POST">
    <fieldset>
        <legend>Registration form</legend>
        <br/><label for="login"><strong>Choose login:</strong></label>
        <input id="login" type="text" name="login" required
               placeholder="Login">

        <br/><label for="password"><strong>Choose password:</strong></label>
        <input id="password" type="password" name="password" required
               placeholder="Password">

        <br/><label for="passwordRepeat"><strong>Choose password:</strong></label>
        <input id="passwordRepeat" type="password" name="repeatPassword" required
               placeholder="Repeat password"/>
        <br/><label><strong>Choose role:</strong>
        <input type="radio" name="role" value="USER" required> USER
        <input type="radio" name="role" value="ADMIN" required> ADMIN
        <input type="radio" name="role" value="GRAND_USER" required> GRAND_USER
    </label>

        <input type="submit" value="Submit"/>
    </fieldset>
</form>

</body>
</html>
