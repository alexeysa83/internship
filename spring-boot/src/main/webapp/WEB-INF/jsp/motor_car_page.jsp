<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta charset="utf-8">
    <title>Registration form</title>
</head>
<body>
<c:if test="${requestScope.get('message') != null}">
    <h4 style="color: #18b239">
            ${requestScope.get('message')}</h4>
</c:if>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Manufacturer</th>
        <th scope="col">Model</th>
        <th scope="col">Engine volume</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cars}" var="car">
        <tr>
            <th scope="row">${car.manufacturer}</th>
            <td>${car.model}</td>
            <td>${car.engineVolume}</td>
            <td>${car.price}</td>
        </tr>
        <sec:authorize access="hasRole('ADMIN')">
            <form action="${pageContext.request.contextPath}/motor_cars/delete/${car.id}" method="POST">
                <input type="submit" value="Delete car"/>
            </form>
        </sec:authorize>
        <sec:authorize access="hasRole('CUSTOMER')">
            <a href="${pageContext.request.contextPath}/sales">Buy</a>
        </sec:authorize>
    </c:forEach>
    </tbody>
</table>
<hr/>
<sec:authorize access="hasRole('ADMIN')">
    <form action="${pageContext.request.contextPath}/motor_cars" method="POST">
        <div class="form-group">
            <label for="manufacturerForm">Manufacturer</label>
            <input type="text" class="form-control" id="manufacturerForm" placeholder="Manufacturer" name="manufacturer"
                   required>
        </div>
        <div class="form-group">
            <label for="modelForm">Model</label>
            <input type="text" class="form-control" id="modelForm" placeholder="Model" name="model"
                   required>
        </div>
        <div class="form-group">
            <label for="engineVolumeForm">Engine volume</label>
            <input type="number" step="0.1" class="form-control form-control-sm" id="engineVolumeForm"
                   placeholder="Engine volume" name="engineVolume"
                   required>
        </div>
        <div class="form-group">
            <label for="priceForm">Price</label>
            <input type="number" step="0.1" class="form-control form-control-sm" id="priceForm" placeholder="Price"
                   name="price"
                   required>
        </div>
        <div class="form-group">
            <label for="bodyForm">Body select</label>
            <select datatype="text" class="form-control" id="bodyForm" name="body" required>
                <option value="SEDAN">SEDAN</option>
                <option value="HATCHBACK">HATCHBACK</option>
                <option value="CROSSOVER">CROSSOVER</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</sec:authorize>

</body>
</html>


