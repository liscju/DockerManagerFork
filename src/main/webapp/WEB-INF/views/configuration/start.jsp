<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DockerManager Login</title>
    <%@ include file="/resources/html/includes.html" %>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
  <form class="form-signin" action="${pageContext.request.contextPath}/configure" method="post">
    <h2 class="form-signin-heading">Fill address of the docker</h2>
    <label for="address" class="sr-only">Address</label>
    <input type="text" id="address" name="address" class="form-control" placeholder="Address" required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>

</div>
<!-- /container -->
</body>
</html>
