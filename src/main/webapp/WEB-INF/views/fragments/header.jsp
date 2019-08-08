<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CP24
  Date: 04.08.2019
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<c:url  var="skills" value="/skills"/>
<c:url  var="register" value="/register"/>
<c:url  var="login" value="/login"/>
<c:url  var="logout" value="/logout"/>
<c:url  var="sources" value="/sources"/>
<c:url  var="unknownsources" value="/unknown-sources"/>


  <header>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                  <li class="nav-item active">
                      <a class="nav-link" href="${skills}">Skills <span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item active">
                      <a class="nav-link" href="${register}">Register <span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item active">
                      <a class="nav-link" href="${login}">Login <span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item active">
                      <a class="nav-link" href="${logout}">Logout <span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item active">
                      <a class="nav-link" href="${sources}">Sources <span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item active">
                      <a class="nav-link" href="${unknownsources}">Unknown sources <span class="sr-only">(current)</span></a>
                  </li>

              </ul>
          </div>
      </nav>

  </header>

