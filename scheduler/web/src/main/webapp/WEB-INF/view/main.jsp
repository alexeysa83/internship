<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta charset="utf-8">
</head>
<body>


<a href="${pageContext.request.contextPath}/admin">Admin page</a>
<a href="${pageContext.request.contextPath}/grand_user">Grand User page</a>

<sec:authorize access="isAuthenticated()">
    <a>
        <sec:authentication property="principal.username"/></a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <a href="${pageContext.request.contextPath}/users/forward_to_registration_form">Registration</a>
</sec:authorize>
<hr/>
<c:if test="${user != null}">
    <br/>${user.id}
    <br/>${user.login}
    <br/>${user.password}
    <br/>${user.role}
</c:if>
</body>
</html>