<%--
  Created by IntelliJ IDEA.
  User: aplastunov
  Date: 23.10.15
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<html>
<head>
  <meta charset="utf-8">
  <title>
    Chocolate management System
  </title>
  <meta name="author" content="Charlie">

  <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/favicon.ico">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/login.jsp">ZN Chocolate</a>
    </div>
  </div>
</nav>
<form class="form-horizontal" role = "form" action="${pageContext.request.contextPath}/login" method="post">

  <div class = "form-group form-group-lg">
    <div class="col-xs-2">
    <label for = "userName">Username</label>
    <input type = "text" class = "form-control" id = "userName" placeholder = "Username...">
    <label for = "inputPassword">Password</label>
    <input type = "password" class = "form-control" id = "inputPassword" placeholder = "Password...">
    </div>
  </div>
  <button type = "submit" class = "btn btn-default">Login</button>
</form>
</body>
</html>

