<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title>DockerManager About</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/image_run_console.js"></script>
</head>
<body>
<%@ include file="/resources/html/menu.html" %>

<div class="container">
  <div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
      <div class="initialization_container">
        <h1>Wait for initialization of console</h1>
      </div>
      <div class="console_container">
        <h1>Console container</h1>
        <input type="text" id="command">
        <button type="button" onclick="exec_command()">Run command</button>
        <br>
        <textarea rows="20" cols="80" id="command_output"></textarea>
      </div>
    </div>
    <div class="col-md-1"></div>
  </div>
</div>

<script type="application/javascript">
  contextPath = "${pageContext.request.contextPath}";
  $(document).ready( function () {
    imageId = "${imageId}";
    $.post(contextPath + "/home/containers/create_container",{ imageId : imageId },function (data) {
      containerId = data;

      hide_initialization_div();
      show_console_div();
    }).fail(function () {
      console.log("Nie udalo sie");
    });

    hide_console_div();
  });
</script>
</body>
</html>
