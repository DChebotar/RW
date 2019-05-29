<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Chebotar_do
  Date: 22.05.2019
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<h3>Hello, ${user.name}</h3>

<h4><a href="/logout">Logout</a></h4>

<security:authorize access="hasAnyAuthority('ADMIN')" var="isAdmin"><h4><a href="/admin">Go to administration</a></h4></security:authorize>


<c:if test="${errormsg != null}">
    ${errormsg}
</c:if>

<table>
    <caption>Choose a train</caption>
    <tr>
        <th>Route</th>
        <th>Arrival time</th>
        <th>Departure time</th>
        <th>Carrage type</th>
        <th>Tickets available</th>
        <th></th>
    </tr>
    <c:forEach var="train" items="${trains}" varStatus="count">
        <tr>
            <th>${train.route}</th>
            <th>${train.arrivalTime}</th>
            <th>${train.departureTime}</th>
            <th><c:forEach var="type" items="${types}">
                <div>${type}</div>
            </c:forEach></th>

            <th><a href="/buy/${train.id}">Buy ticket</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
