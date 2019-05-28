<%--
  Created by IntelliJ IDEA.
  User: Chebotar_do
  Date: 21.05.2019
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Login page</title>
</head>

<body>

<c:if test="${msgerror != null}">
    ${msgerror}
</c:if>

<form action="/login/process" method="post">
  <div>
    <label>Login : </label>
    <div>
      <input type="text" name="login" placeholder="Login">
    </div>
  </div>

  <div>
    <label>Password : </label>
    <div>
      <input type="password" name="password" placeholder="Password">
    </div>
  </div>

  <div>
    <input type="submit" value="Sign In">
  </div>

  <div>
    <a href="/registration">Registration</a>
  </div>

</form>

</body>

</html>
