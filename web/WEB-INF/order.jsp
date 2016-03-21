<%@ page import="ZN_Chocolate.Model.Chocotypes" %>
<%--
  Created by IntelliJ IDEA.
  User: aplastunov
  Date: 23.10.15
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/overview">ZN Chocolate</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/#">Make life easier!</a></li>
                <li><a href="${pageContext.request.contextPath}/settings">Personality</a></li>
            </ul>
        </div>
        <div>
            <p class = "navbar-text navbar-right">
                <a href = "auth?action=logout" class = "navbar-link">Logout</a>
            </p>
        </div>
    </div>
</nav>
<form role = "form" action="${pageContext.request.contextPath}/order" method="post" name="orderForm" onsubmit="PlaceOrder();">
    <div class = "well well-lg">
    <div class = "panel panel-primary">
        <div class = "panel-heading">
            <h3 class = "panel-title">
            Ticket information
            </h3>
        </div>
        <div class = "panel-body">
            <div class="col-sm-4">
                <label for="ChocoType">What u want?</label>
                <select class="form-control" id="ChocoType">
                    <%
                        Chocotypes types = new Chocotypes();
                        String[] options = types.getTypes();
                        for(int i = 0; i < options.length; ++i){
                            out.println("<option>" + options[i] +"</option>");
                        }
                    %>
                </select>
                <label for="ChocoReason">Give us the maximum information on subject</label>
                <input type="text" class="form-control" id="ChocoReason">
            </div>
        </div>

    </div>
    <div class = "panel panel-info">
        <div class = "panel-heading">
            <h3 class = "panel-title">
                Applicant information
            </h3>
        </div>
        <div class = "panel-body">
            <div class="row">
                <div class="col-sm-4">
                    <label for="Name">Your full name</label>
                    <input type="text" class="form-control" id="Name">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <label for="Address">Address to send the reply</label>
                    <input type="text" class="form-control" id="Address">
                </div>
            </div>
        </div>
    </div>
    </div>
    <input type="hidden" class="form-control" name="orderName">
    <input type="hidden" class="form-control" name="order">
    <button type="submit" name="submit" class="btn btn-default" id="submit">Upload</button>
</form>
<%
    if (request.getAttribute("result") != null){
        if (request.getAttribute("Error") == "true") {
            out.println("<div class = \"alert alert-danger\">");
            out.println(request.getAttribute("result"));
            out.println("</div>");
        }else{
            out.println("<div class = \"alert alert-success\">");
            out.println("You can check your order <a href=\"" + request.getAttribute("result") + "\">here</a>");
            out.println("</div>");
        }
    }

        %>
<script>
    function PlaceOrder(){
        var inputs = document.querySelectorAll("input[type=text]")
        var select = document.orderForm.ChocoType.value
        var order = ""
        for (i = inputs.length-1; i >= 0; --i) {
            order+=inputs[i].value
            order+="\r\n"
        }
        order+=select
        document.orderForm.order.value = order;
        document.orderForm.orderName.value = makeid();
        return true;
    }
    function makeid()
    {
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for( var i=0; i < 5; i++ )
            text += possible.charAt(Math.floor(Math.random() * possible.length));

        return text;
    }
</script>
</body>
</html>

