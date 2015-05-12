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
    <h1>DockerManager</h1>

    <p class="lead">Container id: ${id}, image: ${image}</p>
  </div>
</div>

    <table class="table">
      <thead>
      <tr>
        <th>Parameter</th>
        <th>Value</th>
      </tr>
      </thead>
      <tbody>
          <tr>
            <td>ID</td>
            <td>${imageS.id}</td>
          </tr>
          <tr>
            <td>Created</td>
            <td>${created}</td>
          </tr>
          <tr>
            <td>Size</td>
            <td>${imageS.size}</td>
          </tr>
      </tbody>
    </table>

</body>
</html>
