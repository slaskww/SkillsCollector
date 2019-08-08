<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CP24
  Date: 07.08.2019
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="col-md-12 text-center">
    <h2>Zarejestruj się do aplikacji</h2>
</div>
<div>
    <form method="post" action="/register">
        <div class="form-group col-md-6 offset-md-3 p-2 mb-1 bg-light text-dark">
            <label>Imię:</label>
            <input class="form-control" type="text" name="firstName" id="firstName">
            <c:if test="${requestScope.get('errorMap').containsKey('firstName')}">
                <small class="text-danger">${requestScope.get('errorMap').get('firstName')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-6 offset-md-3 p-2 mb-1 bg-light text-dark">
            <label>Nazwisko:</label>
            <input class="form-control" type="text" name="lastName" id="lastName">
            <c:if test="${requestScope.get('errorMap').containsKey('lastName')}">
                <small class="text-danger">${requestScope.get('errorMap').get('lastName')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-6 offset-md-3 p-2 mb-1 bg-light text-dark">
            <label>Nazwa użytkownika:</label>
            <input class="form-control" type="text" name="username" id="username">
            <c:if test="${requestScope.get('errorMap').containsKey('username')}">
                <small class="text-danger">${requestScope.get('errorMap').get('username')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-6 offset-md-3 p-2 mb-1 bg-light text-dark">
            <label>Hasło:</label>
            <input class="form-control" type="password" name="password" id="password">
            <c:if test="${requestScope.get('errorMap').containsKey('password')}">
                <small class="text-danger">${requestScope.get('errorMap').get('password')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-6 offset-md-3 p-2 mb-1 bg-light text-dark">
            <label>Powtórz hasło:</label>
            <input class="form-control" type="password" name="confirmedPassword" id="confirmedPassword">
            <c:if test="${requestScope.get('errorMap').containsKey('confirmedPassword')}">
                <small class="text-danger">${requestScope.get('errorMap').get('confirmedPassword')}</small>
            </c:if>
        </div>
        <div>
            <input class="btn btn-primary col-md-4 offset-md-4" type="submit" value="Zarejestruj">
        </div>
    </form>
</div>
<div class="fixed-bottom text-center p-2 mb-2 bg-light text-dark">
    <jsp:include page="fragments/footer.jsp"/>
</div>


</body>
</html>
