<%--
  Created by IntelliJ IDEA.
  User: Chebotar_do
  Date: 23.05.2019
  Time: 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>

<c:if test="${error != null}">
    ${error}
</c:if>

<form action="/registration" method="post">
    <div>
        <label>Login : </label>
        <div>
            <input type="text" name="login" placeholder="Login" value="${login != null ? login : ''}">
        </div>
    </div>
    <div>
        <label>Password : </label>
        <div>
            <input type="password" name="password" placeholder="Password">
        </div>
    </div>
    <div>
        <label>Confirm Password : </label>
        <div>
            <input type="password" name="confirmPassword" placeholder="Confirm Password">
        </div>
    </div>
    <div>
        <label>Name : </label>
        <div>
            <input type="text" name="name" placeholder="Name" value="${name != null ? name : ''}">
        </div>
    </div>
    <div>
        <label>Patronymic : </label>
        <div>
            <input type="text" name="patronymic" placeholder="Patronymic" value="${patronymic != null ? patronymic : ''}">
        </div>
    </div>
    <div>
        <label>Surname : </label>
        <div>
            <input type="text" name="surname" placeholder="Surname" value="${surname != null ? surname : ''}">
        </div>
    </div>
    <div>
        <label>Passport Series : </label>
        <div>
            <input type="text" name="passportSeries" placeholder="Passport Series" value="${passportSeries != null ? passportSeries : ''}">
        </div>
    </div>
    <div>
        <label>Passport Number : </label>
        <div>
            <input type="text" name="passportNumber" placeholder="Passport Number" value="${passportNumber != null ? passportNumber : ''}">
        </div>
    </div>
    <div>
        <label>Passport IssueDate : </label>
        <div>
            <input type="date" name="passportIssueDate" placeholder="Passport IssueDate" value="${passportIssueDate != null ? passportIssueDate : ''}">
        </div>
    </div>
    <div>
        <label>Passport Issue By : </label>
        <div>
            <input type="text" name="passportIssueBy" placeholder="Passport Issue By" value="${passportIssueBy != null ? passportIssueBy : ''}">
        </div>
    </div>
    <div>
        <label>Email : </label>
        <div>
            <input type="email" name="email" placeholder="Email" value="${email != null ? email : ''}">
        </div>
    </div>
    <div>
        <input type="submit" value="Registration">
    </div>

</form>

</body>
</html>
