<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Servers</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
</head>
<body>
<%@ include file="/resources/html/menu.html" %>

<div class="container">
  <div class="starter-template">
    <h1>DockerManager</h1>

    <p class="lead">Servers</p>

    <table class="table">
      <thead>
      <tr>
        <th>ServerID</th>
        <th>Name</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="server" items="${servers}">
          <tr>
            <td>${server.serverID}</td>
            <td>${server.name}</td>
            <td><a href="${pageContext.request.contextPath}/home/servers/${server.serverID}">Show details</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

  </div>

  <form role="form" id="add_server" method="post">
    <div class="form-group">
      <label for="server_name">Name:</label>
      <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
    </div>
    <div class="form-group">
      <label for="server_address">Address:</label>
      <input type="text" class="form-control" id="address" name="address" placeholder="Enter sddress">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

</body>
</html>
