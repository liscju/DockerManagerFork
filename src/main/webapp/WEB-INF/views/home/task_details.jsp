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
<%
  // Set refresh, autoload time as 5 seconds
  response.setIntHeader("Refresh", 5);
%>

<div class="container">
    <div class="starter-template">
      <h1>Task Info</h1>
    </div>
    <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-10">
        <table class="table">
          <tbody>
          <tr>
            <td>
              <b>ID</b>
            </td>
            <td>
              ${task.id}
            </td>
          </tr>
          <tr>
            <td>
              <b>Properties</b>
            </td>
            <td>
              ${task.properties}
            </td>
          </tr>
          <tr>
            <td>
              <b>Status</b>
            </td>
            <td>
              <c:choose>
                <c:when test="${task.status.name == 'BEGGINING'}">BEGGINNG</c:when>
                <c:when test="${task.status.name == 'INPROGRESS'}">INPROGRESS</c:when>
                <c:when test="${task.status.name == 'SUCCESS_END'}">SUCCESS_END</c:when>
                <c:when test="${task.status.name == 'FAILURE_END'}">FAILURE_END</c:when>
                <c:otherwise>BAD</c:otherwise>
              </c:choose>
            </td>
          </tr>
          </tbody>
        </table>
        <c:if test="${task.status.name == 'FAILURE_END'}">
          ${task.errResult}
        </c:if>
      </div>
      <div class="col-md-1"></div>
    </div>
    <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-10">
        <h2>Output:</h2>
        <ul>
          <c:forEach var="message" items="${messages}">
            <li>${message.details}</li>
          </c:forEach>
        </ul>
      </div>
      <div class="col-md-1"></div>
    </div>
</div>

</body>
</html>
