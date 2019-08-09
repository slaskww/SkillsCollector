<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CP24
  Date: 08.08.2019
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Unknown sources</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<table class="table col-md-6 offset-md-3">
    <thead class="thead-light">
    <tr>
        <th scope="col" colspan="5">Źródła umiejętności nieznane użytkownikowi ${sessionScope.get('user').getUsername()}</th>
    </tr>
    <tr>
        <th scope="col">#</th>
        <th scope="col">nazwa źródła</th>
        <th scope="col">opis</th>
        <th scope="col">kreowane umiejętności</th>
        <th scope="col">akcje</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.get('unknownSourcesMap')}" var="source" varStatus="lp">
        <tr>
            <th scope="row">${lp.count}</th>
            <td>${source.getKey().getName()}</td>
            <td>${source.getKey().getDescription()}</td>
            <td>${source.getValue()}</td>
            <td>empty</td>
        </tr>

    </c:forEach>
    </tbody>
</table>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>