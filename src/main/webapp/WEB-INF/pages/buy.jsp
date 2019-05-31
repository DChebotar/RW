<%--
  Created by IntelliJ IDEA.
  User: Chebotar_do
  Date: 31.05.2019
  Time: 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Buy page</title>
    <meta charset="utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
    <script>
        function getTypesOfCarrages(train_id) {

            $.ajax({

                dataType: 'JSON',
                type: 'POST',
                url: '/main/types',
                data: {trainid:train_id},
                success: function (types) {
                    console.log(types);
                    for (var i = 0; i < types.length; i++) {
                        $('#chooseType').append("<option>" + types[i] + "</option>")
                    }
                }
            });
            return false;
        };

        function getNuberOfCarrage(train_id, type) {

            console.log(type);
            $.ajax({

                dataType: 'JSON',
                type: 'POST',
                url: '/buy/getcarragenumber',
                data: {trainid:train_id,typeCar:type},
                success: function (numbers) {
                    console.log(numbers);
                    for (var i = 0; i < numbers.length; i++) {
                        $('#carragenumbers').append("<option>" + numbers[i] + "</option>")
                    }
                }
            });
            return false;
        }
        ;

        function getFreeTicketsInCarrage(train_id, number) {

            console.log(train_id, number);
            $.ajax({

                dataType: 'JSON',
                type: 'POST',
                url: '/buy/getseats',
                data: {trainid:train_id,carrageNumber:number},
                success: function (seats) {
                    console.log(seats);
                    for (var i = 0; i < seats.length; i++) {
                        $('#seats').append("<option>" + seats[i] + "</option>")
                    }
                }
            });
            return false;
        }
        ;
        </script>

</head>
<body>
<table>
    <tr>
        <th>Route</th>
        <th>Arrival time</th>
        <th>Departure time</th>
    <tr>
        <th>${train.route}</th>
        <th>${fn:substring(train.arrivalTime, 0, 16)}</th>
        <th>${fn:substring(train.departureTime, 0, 16)}</th>
    </tr>
</table>

<div>
    <form action="/buy/getTicket" method="post">
        <input name="id" type="hidden" value="${train.id}">
        <div>
            <label>Choose type of carrage : </label>
            <script>getTypesOfCarrages(${train.id})</script>
            <select id="chooseType" name="carrageType"   onchange="getNuberOfCarrage(${train.id}, $('#chooseType :selected').text())">
                <option disabled selected>Choose type</option>
            </select>
        </div>

        <div>
            <label>Choose carrage namber : </label>
            <select id="carragenumbers" name="numberOfCarrage" onchange="getFreeTicketsInCarrage(${train.id}, $('#carragenumbers :selected').text())">
                <option disabled selected>Choose number</option>
            </select>
        </div>

        <div>
            <label>Choose seat : </label>
            <select id="seats" name="numberOfSeat">
                <option disabled selected>Choose seat</option>
            </select>
        </div>

        <div>
            <input type="submit" value="Buy ticket">
        </div>

    </form>
</div>

</body>
</html>
