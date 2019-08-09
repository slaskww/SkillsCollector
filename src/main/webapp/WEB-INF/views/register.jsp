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

    <h2 class="h3 mb-3 font-weight-normal text-center">Zarejestruj się do aplikacji</h2>
<div>
    <form method="post" action="/register">
        <div class="form-group col-md-2 offset-md-5 p-0 mb-1 bg-light text-dark">
            <label class="sr-only">Imię:</label>
            <input class="form-control" type="text" name="firstName" id="firstName" placeholder="imię">
            <c:if test="${requestScope.get('errorMap').containsKey('firstName')}">
                <small class="text-danger">${requestScope.get('errorMap').get('firstName')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-2 offset-md-5 p-0 mb-1 bg-light text-dark">
            <label class="sr-only">Nazwisko:</label>
            <input class="form-control" type="text" name="lastName" id="lastName" placeholder="nazwisko">
            <c:if test="${requestScope.get('errorMap').containsKey('lastName')}">
                <small class="text-danger">${requestScope.get('errorMap').get('lastName')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-2 offset-md-5 p-0 mb-1 bg-light text-dark">
            <label class="sr-only">Nazwa użytkownika:</label>
            <input class="form-control" type="text" name="username" id="username" placeholder="nazwa użytkownika">
            <c:if test="${requestScope.get('errorMap').containsKey('username')}">
                <small class="text-danger">${requestScope.get('errorMap').get('username')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-2 offset-md-5 p-0 mb-1 bg-light text-dark">
            <label class="sr-only">Hasło:</label>
            <input class="form-control" type="password" name="password" id="password" placeholder="hasło">
            <c:if test="${requestScope.get('errorMap').containsKey('password')}">
                <small class="text-danger">${requestScope.get('errorMap').get('password')}</small>
            </c:if>
        </div>
        <div class="form-group col-md-2 offset-md-5 p-0 mb-1 bg-light text-dark">
            <label class="sr-only">Powtórz hasło:</label>
            <input class="form-control" type="password" name="confirmedPassword" id="confirmedPassword" placeholder="powtórz hasło">
            <c:if test="${requestScope.get('errorMap').containsKey('confirmedPassword')}">
                <small class="text-danger">${requestScope.get('errorMap').get('confirmedPassword')}</small>
            </c:if>
        </div>
        <div>
            <input class="btn btn-primary col-md-2 offset-md-5" type="submit" value="Zarejestruj">
        </div>
    </form>
</div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
