<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager About</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
</head>
<body>
<%@ include file="/resources/html/menu.html" %>

<div class="container">
  <div class="starter-template">
    TaskId: ${task.id}<br>
    TaskProps: ${task.properties}
    TaskStatus: ${task.status}
  </div>
</div>

</body>
</html>
