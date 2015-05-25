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
    TaskProps: ${task.properties}<br>
    TaskStatus:
    <c:choose>
      <c:when test="${task.status.name == 'BEGGINING'}">BEGGINNG</c:when>
      <c:when test="${task.status.name == 'INPROGRESS'}">INPROGRESS</c:when>
      <c:when test="${task.status.name == 'SUCCESS_END'}">SUCCESS_END</c:when>
      <c:when test="${task.status.name == 'FAILURE_END'}">FAILURE_END</c:when>
      <c:otherwise>BAD</c:otherwise>
    </c:choose>
    <br>
    <c:if test="${task.status.name == 'FAILURE_END'}">
      ${task.errResult}
    </c:if>
  </div>
</div>

</body>
</html>
