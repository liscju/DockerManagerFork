<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Image</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
  <%@ include file="/resources/html/menu.html" %>

  <div class="container">
    <div class="starter-template">
      <h1>DockerManager</h1>

      <p class="lead">Image id: ${imageId}</p>
    </div>

    <form action="${pageContext.request.contextPath}/home/images/run" method="POST">
      <h2>Run quick command in image</h2>
      <input type="hidden" name="imageId" value="${imageId}"/>
      <input type="text" name="command" placeholder="Place for your command"></input>
      <br>
      <input type="submit"/>
    </form>

  </div>
</body>
</html>
