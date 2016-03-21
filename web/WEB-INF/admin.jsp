<%--
  Created by IntelliJ IDEA.
  User: aplastunov
  Date: 22.10.15
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Chocolate Management System
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/overview">ZN Chocolate</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
            </ul>
        </div>
    </div>
</nav>

<%!  boolean isAdmin=false; %>

<% if (isAdmin == false) {
    out.println ("<h1>You are not authorized to be here!</h1>");
    out.println ("Your ip is <b>"+ request.getRemoteAddr() +"</b>. This incident will be reported");

}
else {
    out.println ("<h1>Welcome to administrative interface!</h1>");

}
%>
</body>
</html>