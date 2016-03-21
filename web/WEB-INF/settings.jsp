<%--
  Created by IntelliJ IDEA.
  User: aplastunov
  Date: 23.10.15
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
        %>
<%  String token = request.getAttribute("token").toString(); %>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Chocolate
    </title>
    <meta name="author" content="Charlie">

    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/favicon.ico">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<%
    if (request.getAttribute("Error") != null){
        out.println(request.getAttribute("Error"));
    }
%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/overview">ZN Chocolate</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/order">Make life easier!</a></li>
                <li><a href="${pageContext.request.contextPath}/#">Personality</a></li>
            </ul>
        </div>
        <div>
            <p class = "navbar-text navbar-right">
                <a href = "auth?action=logout" class = "navbar-link">Logout</a>
            </p>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <h3>Note to choco lovers!</h3>
    <p>To get all the sweeties, you ordered, you must generate your personal super secret security token and name it to us if we ask kindly</p>
<br>
    <h4>Current token: <span class = "label label-default">
    <%
     out.println(token);
%></span></h4>
    <h4>Token status: <span class =<% if (token != null && token.length() > 0){
        out.println("\"label label-info\">Processing");
    } else{
        out.println("\"label label-danger\">Expired");

    } %></span></h4>
<a class = "btn btn-primary" href="${pageContext.request.contextPath}/settings?seed=1">Generate new</a>
</div>
</body>
</html>

