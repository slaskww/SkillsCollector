<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CP24
  Date: 07.08.2019
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Skills</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<table class="table col-md-6 offset-md-3">
    <thead class="thead-light">
    <tr>
        <th scope="col" colspan="3" class="text-center">Poziom umiejętności użytkownika ${sessionScope.get('user').getUsername()}</th>
    </tr>
    <tr>
        <th scope="col">#</th>
        <th scope="col">umiejętność</th>
        <th scope="col">poziom opanowania</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.get('skillMap')}" var='skills' varStatus="lp">
        <tr>
            <th scope="row">${lp.count}</th>
            <td>${skills.getKey()}</td>
            <td>${skills.getValue()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="fixed-bottom text-center p-2 mb-2 bg-light text-dark">
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
