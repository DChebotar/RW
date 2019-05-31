<%--
  Created by IntelliJ IDEA.
  User: Chebotar_do
  Date: 22.05.2019
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Main page</title>
    <meta charset="utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>

    <script>
        function getTypesOfCarrages(train_id) {

            $.ajax({

                dataType: 'JSON',
                type: 'POST',
                url: '/main/types',
                data: {trainid: train_id},
                success: function (types) {
                    console.log(types);
                    for (var i = 0; i < types.length; i++) {
                        $('#types' + train_id).append(types[i] + "</br>")
                    }
                }
            });
            return false;
        }
        ;

        function getFreeTickets(train_id) {

            $.ajax({

                dataType: 'JSON',
                type: 'POST',
                url: '/main/freetickets',
                data: {trainid: train_id},
                success: function (tickets) {
                    console.log(tickets);
                    for (var i = 0; i < tickets.length; i++) {
                        $('#tickets' + train_id).append(tickets[i] + "</br>")
                    }
                }
            });
            return false;
        }
        ;

    </script>

</head>
<body>

<h3>Hello, ${user.name}</h3>

<h4><a href="/logout">Logout</a></h4>

<h4><a href="/returnticket">Return ticket</a></h4>

<security:authorize access="hasAnyAuthority('ADMIN')" var="isAdmin"><h4><a href="/admin">Go to administration</a>
</h4></security:authorize>

<c:if test="${errormsg != null}">
    ${errormsg}
</c:if>

<form action="/search" method="post">
    <label>From : </label>
    <input type="text" name="arrStation" placeholder="Arrival station">

    <label>To : </label>
    <input type="text" name="depStation" placeholder="Departure station">

    <input type="submit" value="Search">

</form>

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
            <th>${fn:substring(train.arrivalTime, 0 ,16)}</th>
            <th>${fn:substring(train.departureTime, 0, 16)}</th>
            <script>getTypesOfCarrages(${train.id})</script>
            <th id="types${train.id}"></th>
            <script>getFreeTickets(${train.id})</script>
            <th id="tickets${train.id}"></th>
            <th><a href="/buy/${train.id}">Buy ticket</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
