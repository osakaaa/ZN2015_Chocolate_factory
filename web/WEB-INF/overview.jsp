<%--
Created by IntelliJ IDEA.
User: aplastunov
Date: 22.10.15
Time: 11:11
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.io.File"%>
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
  Cookie UserCookie = null;
  Cookie[] cookies = null;
  cookies = request.getCookies();
  if( cookies != null ){
    for (int i = 0; i < cookies.length; i++){
      if (cookies[i].getName().equals("ChocoUser")){UserCookie = cookies[i];}
    }
  }
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/#">ZN Chocolate</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/order">Make life easier!</a></li>
        <li><a href="${pageContext.request.contextPath}/settings">Personality</a></li>
      </ul>
    </div>
    <div>
      <p class = "navbar-text navbar-right">
        Signed in as
        <a href = "#" class = "navbar-link"><% if (UserCookie != null){
        out.println(UserCookie.getValue());
        }else{
          out.println("Unknown");
          }%></a>!
      <a href = "${pageContext.request.contextPath}/auth?action=logout" class = "navbar-link">Logout?</a>
      </p>
    </div>
  </div>
</nav>
<%
  if (response.getStatus() == 0){
     if (UserCookie != null){
        out.println("<div class = \"alert alert-success\">");
        out.println("Welcome to society!," + UserCookie.getValue());
        out.println("</div>");

      }
  }%>

</body>
</html>
