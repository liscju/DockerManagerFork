<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>DockerManager Login</title>
    <%@ include file="/resources/html/includes.html" %>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
  <div class="row">
      <div class="col-xs-4 text-left">
      </div>
      <div class="col-xs-2 text-left">
          <form method="POST" action="${pageContext.request.contextPath}/set_language_eng">
              <button class="btn btn-lg btn-primary" type="submit">ENG</button>
          </form>
      </div>
      <div class="col-xs-2 text-left">
          <form method="POST" action="${pageContext.request.contextPath}/set_language_pl">
              <button class="btn btn-lg btn-primary" type="submit">PL</button>
          </form>
      </div>
      <div class="col-xs-4 text-left">
      </div>
  </div>
  <form class="form-signin" action="${pageContext.request.contextPath}/configure" method="post">
    <h2 class="form-signin-heading">
        <spring:message code="configuration.start.fill_address" text="missing" />
    </h2>
    <label for="address" class="sr-only">
        <spring:message code="configuration.start.address" text="missing" />
    </label>
    <input type="text" id="address" name="address" class="form-control"
           placeholder="<spring:message code="configuration.start.address" text="missing" />" required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit">
        <spring:message code="configuration.start.sign_in" text="missing" />
    </button>
  </form>

</div>
<!-- /container -->
</body>
</html>
