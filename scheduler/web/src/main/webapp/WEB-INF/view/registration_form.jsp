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
        <label for="login"><strong>Choose login:</strong></label>
        <input id="login" type="text" name="login" required
               placeholder="Login">

        <label for="password"><strong>Choose password:</strong></label>
        <input id="password" type="password" name="password" required
               placeholder="Password">

        <label for="passwordRepeat"><strong>Choose password:</strong></label>
        <input id="passwordRepeat" type="password" name="repeatPassword" required
               placeholder="Repeat password"/>
        <input type="submit" value="Submit"/>
    </fieldset>
</form>

</body>
</html>
