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

      <p class="lead">Images</p>

      <table class="table">
        <thead>
        <tr>
          <th>Id</th>
          <th>Tag</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach var="image" items="${images}">
            <tr>
              <td>${image.id}</td>
              <td>${image.tag}</td>
              <td><a href="${pageContext.request.contextPath}/home/images/${image.id}">Show details</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <form role="form" action="${pageContext.request.contextPath}/home/images/add_image_from_dockerfile" method="post">
        <h2>Create Image from dockerfile</h2>
        Name<br>
        <input type="text" name="image_name"/>
        <br>
        File<br>
        <textarea rows="10" cols="80" name="dockerfile"></textarea>
        <br>
        <input type="submit"/>
      </form>

    </div>
  </div>
</body>
</html>
