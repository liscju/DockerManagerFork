<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Containers</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
<%@ include file="/resources/html/menu.html" %>

<div class="container">
  <div class="starter-template">
    <h1>DockerManager Container Details</h1>

    <p class="lead">
      Container id: ${container.id} <br>
      Container names: ${container.names} <br>
      container image: ${container.image} <br>
      container status: ${container.status}
    </p>

    <p class="lead">
      Exposed Interfaces: ${container.interfaces}
    </p>
  </div>

  <form action="${pageContext.request.contextPath}/home/containers/stop_container" method="POST">
    <h2>Stop container</h2>
    <br>
    <input type="hidden" name="containerId" value="${container.id}"/>
    <input class="btn btn-default" type="submit"/>
  </form>

  <form action="${pageContext.request.contextPath}/home/containers/delete_container" method="POST">
    <h2>Delete container</h2>
    <br>
    <input type="hidden" name="containerId" value="${container.id}"/>
    <input class="btn btn-default" type="submit"/>
  </form>


</div>

</body>
</html>
