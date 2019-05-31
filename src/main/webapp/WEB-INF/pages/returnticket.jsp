<%--
  Created by IntelliJ IDEA.
  User: Chebotar_do
  Date: 31.05.2019
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Return ticket</title>
</head>
<body>
<c:if test="${errormsg != null}">
    ${errormsg}
</c:if>
<table>
    <caption>Your tickets</caption>

    <tr>
        <th>Ticket number</th>
        <th>Route</th>
        <th>Arrival time</th>
        <th>Departure time</th>
        <th>Carrage type</th>
        <th>Carrage number</th>
        <th>Seat number</th>
    </tr>

    <c:forEach var="ticket" items="${tickets}">
        <tr>
            <th>${ticket.id}</th>
            <th>${ticket.seat.carrage.train.route}</th>
            <th>${ticket.seat.carrage.train.arrivalTime}</th>
            <th>${ticket.seat.carrage.train.departureTime}</th>
            <th>${ticket.seat.carrage.carrageType}</th>
            <th>${ticket.seat.carrage.carrageNumber}</th>
            <th>${ticket.seat.number}</th>
            <th><a href="/return/${ticket.id}">Return ticket</a></th>
        </tr>
    </c:forEach>

</table>

</body>
</html>
