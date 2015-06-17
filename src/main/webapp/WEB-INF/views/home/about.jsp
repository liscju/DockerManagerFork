<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
    <h1>DockerManager</h1>

    <p class="lead"><spring:message code="home.about.about_application" text="missing" /></p>

    <p class="lead">
      <spring:message code="home.about.contact" text="missing" /><br>
      <spring:message code="home.about.github_link_proposal" text="missing" /><a href="https://github.com/liscju/DockerManager" target="_blank">DockerManager</a>
    </p>
  </div>
</div>

</body>
</html>
