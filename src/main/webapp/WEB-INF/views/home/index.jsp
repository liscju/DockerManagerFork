<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DockerManager Home</title>
    <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/resources/lib/jquery/jquery-2.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/commons/menu.html" %>

    <div class="container">
        <div class="starter-template">
            <h1>DockerManager</h1>
            <p class="lead">Greeting : ${greeting}</p>
        </div>
    </div>

</body>
</html>
