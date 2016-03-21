<%--
  Created by IntelliJ IDEA.
  User: aplastunov
  Date: 25.10.15
  Time: 15:07
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
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/overview">ZN Chocolate</a>
        </div>
        <form class="navbar-search pull-right" method="post" action="${pageContext.request.contextPath}/auth">
            <input type="text" class="span2" name="username" placeholder="Login">
            <input type="password" class="span2" name="password" placeholder="Password">
            <input type="hidden" class="span2" name="action" value="login">
            <button type="submit" name="submit" class="btn btn-default">Sign In</button>
            <p class = "text-muted text-right">
                or <a href = "${pageContext.request.contextPath}/auth?action=signup" class = "navbar-link">Sign up</a> if you don't have an account
            </p>
        </form>
    </div>
</nav>
<div class="container-fluid">
    <%
        if (request.getAttribute("Error") != null) {
            out.println("<div class = \"alert alert-danger\">");
            out.println(request.getAttribute("Error"));
            out.println("</div>");
        }

    %>
<h2>Global life simplification system</h2>
<br>
<p>You can read find more information about the System on our official <a target="_blank" href="http://www.4chan.org/">blog</a></p>
<p>Stay tuned for more sweeties!</p>
</div>

</body>
</html>

