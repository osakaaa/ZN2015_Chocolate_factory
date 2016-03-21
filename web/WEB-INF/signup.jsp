<%--
  Created by IntelliJ IDEA.
  User: aplastunov
  Date: 26.10.15
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/overview">ZN Chocolate</a>
        </div>
        <form class="navbar-search pull-right">
            <input type="text" class="span2" name="username" placeholder="Login">
            <input type="password" class="span2" name="password" placeholder="Password">
            <input type="hidden" class="span2" name="action" value="login">
            <button type="submit" name="submit" class="btn btn-default">Sign In</button>
            <p class = "text-muted text-right">
                or <a href = "#" class = "navbar-link">Sign up</a> if you don't have an account
            </p>
        </form>
    </div>
</nav>
<form class = "form-horizontal" role = "form" method="post" action="${pageContext.request.contextPath}/auth">

    <div class = "form-group">
        <label for = "username" class = "col-sm-2 control-label">Login</label>

        <div class = "col-sm-2">
            <input type = "text" class = "form-control" name="username" id = "username" placeholder = "Enter Login name">
        </div>
    </div>

    <div class = "form-group">
        <label for = "password" class = "col-sm-2 control-label">Password</label>

        <div class = "col-sm-2">
            <input type = "password" class = "form-control" name="password" id = "password" placeholder = "Enter Password">
        </div>
    </div>
    <input type = "hidden" class = "form-control" id="action" name="action" value="signup">
    <div class = "form-group">
        <div class = "col-sm-offset-2 col-sm-10">
            <button type = "submit" name="submit" class = "btn btn-default">Sign up</button>
        </div>
    </div>
    <div class="g-recaptcha" data-sitekey="6LfZHRATAAAAAB8Uz-0dGRXYgH3OOz74lsWjPoDW"></div>
</form>
<%
        if (request.getAttribute("Error") != null) {
            out.println("<div class = \"alert alert-danger\">");
            out.println(request.getAttribute("Error"));
            out.println("</div>");
        }

%>
</body>
</html>

