<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DockerManager Login</title>
    <%@ include file="/resources/html/includes.html" %>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet"/>
</head>
<body>
<div class="container">

  <form class="form-signin" method="post">
    <h2 class="form-signin-heading">Please log in</h2>
    <label for="username" class="sr-only">Username</label>
    <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>

</div>
<!-- /container -->
</body>
</html>
