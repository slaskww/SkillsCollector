<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CP24
  Date: 07.08.2019
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="col-md-12 text-center">
    <h2>Logowanie</h2>
</div>
<form action="/login" method="post">
    <div class="form-group col-md-6 offset-md-3 p-2 mb-1 bg-light text-dark">
        <label>nazwa użytkownika</label>
        <input class="form-control" type="text" id="username" name="username">
    </div>
    <div class="form-group col-md-6 offset-md-3 p-2 mb-1 bg-light text-dark">
        <label>hasło</label>
        <input class="form-control" type="password" id="password" name="password">
        <c:if test="${requestScope.containsKey('errorMsg')}">
            <small class="text-danger">${requestScope.get('errorMsg')}</small>
        </c:if>
    </div>
    <div>
        <input class="btn btn-primary col-md-4 offset-md-4" type="submit" value="Zarejestruj">
    </div>
    <div class="fixed-bottom text-center p-2 mb-2 bg-light text-dark">
        <jsp:include page="fragments/footer.jsp"/>
    </div>

</form>

</body>
</html>
