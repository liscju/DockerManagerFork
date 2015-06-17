<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title>DockerManager Containers</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath}/resources/css/container_details.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
<%@ include file="/resources/html/menu.html" %>

<div class="container">
  <div class="row">
    <div class="starter-template">
      <h1>Container Info</h1>
    </div>
  </div>
  <div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">

      <form action="${pageContext.request.contextPath}/home/containers/stop_container" method="POST">
        <input type="hidden" name="containerId" value="${container.id}"/>
        <button type="submit" class="btn btn-warning">Stop Container</button>
      </form>

      <form action="${pageContext.request.contextPath}/home/containers/delete_container" method="POST">
        <input type="hidden" name="containerId" value="${container.id}"/>
        <button type="submit" class="btn btn-danger">Delete Container</button>
      </form>
    </div>
    <div class="col-md-1"></div>
  </div>
  <div class="row voffset2">
    <div class="col-md-1"></div>
    <div class="col-md-10">
      <table class="table">
        <tbody>
        <tr>
          <td>
            <b>ID</b>
          </td>
          <td>
            ${container.id}
          </td>
        </tr>
        <tr>
          <td>
            <b>Names</b>
          </td>
          <td>
            ${container.names}
          </td>
        </tr>
        <tr>
          <td>
            <b>Image</b>
          </td>
          <td>
            ${container.image}
          </td>
        </tr>
        <tr>
          <td>
            <b>Status</b>
          </td>
          <td>
            <span
              <c:choose>
                <c:when test="${fn:contains(container.status, 'Up')}">class="status-up"</c:when>
                <c:when test="${fn:contains(container.status, 'Stop')}">class="status-stop"</c:when>
                <c:when test="${fn:contains(container.status, 'Exited')}">class="status-exit"</c:when>
                <c:otherwise>class="error"</c:otherwise>
              </c:choose>
            >
              ${container.status}
            </span>
          </td>
        </tr>
        <tr>
          <td>
            <b>Interfaces</b>
          </td>
          <td>
            ${container.interfaces}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="col-md-2"></div>
  </div>

</div>

</body>
</html>
