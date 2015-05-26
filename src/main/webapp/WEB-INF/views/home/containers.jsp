<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Containers</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath}/resources/css/containers.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
<%@ include file="/resources/html/menu.html" %>

<div class="container">
  <div class="starter-template">
    <table class="table">
      <thead>
      <tr>
        <th>Id</th>
        <th>Names</th>
        <th>Image</th>
        <th>Status</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="container" items="${containers}">
          <tr>
            <td>${fn:substring(container.id,0,10)}...</td>
            <td>${container.names}</td>
            <td>${fn:substring(container.image,0,10)}...</td>
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
            <td><a href="${pageContext.request.contextPath}/home/containers/${container.id}">Show details</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

  </div>

</div>

</body>
</html>
