<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CP24
  Date: 09.08.2019
  Time: 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Confirm source</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<form action="/confirm?id=${requestScope.get('sourceToConfirm').getId()}" method="post">
    <div class="jumbotron col-md-8 offset-md-2">
        <h1 class="display-4">Potwierdź źródło</h1>
        <p class="lead">Klinik zatwierdź, by dodać do listy nowe źródło wiedzy</p>
        <hr class="my-4">
        <p>Dodawane źródło wiedzy: ${requestScope.get('sourceToConfirm').getName()} (${requestScope.get('sourceToConfirm').getDescription()})</p>
        <p class="lead">
            <input type="submit" class="btn btn-primary btn-lg" role="button" value="Zatwierdź">
        </p>
    </div>

</form>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
