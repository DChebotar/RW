<%--
  Created by IntelliJ IDEA.
  User: Chebotar_do
  Date: 27.05.2019
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<h3>${user.name}</h3>
<h4><a href="/logout">Logout</a></h4>

<h3>Create train</h3>

<form action="/admin/createtrain" method="post">

    <div>
        <label>Arrival station : </label>

        <div>
            <input type="text" name="arrStation" placeholder="Arrival station"
                   value="${arrStation != null ? arrStation : ''}">
        </div>
    </div>
    <div>
        <label>Departure station : </label>

        <div>
            <input type="text" name="depStation" placeholder="Departure station"
                   value="${depStation != null ? depStation : ''}">
        </div>
    </div>
    <div>
        <label>Carrages : </label>
        <c:forEach items="${types}" var="type" varStatus="count">
            <label>${type}</label>
            <input type="text" name="numberOfCarrages${count.getIndex()}">
        </c:forEach>
    </div>
    <div>
        <label>Arrival time : </label>
        <div>
            <input type="datetime-local" name="arrTime">
        </div>
    </div>
    <div>
        <label>Departure time : </label>
        <div>
            <input type="datetime-local" name="depTime">
        </div>
    </div>

    <div>
        <input type="submit" value="Create train">
    </div>
</form>
</body>
</html>
