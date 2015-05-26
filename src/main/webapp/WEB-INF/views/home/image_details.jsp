<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Image Details</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lib/bootstrap-vertical-tabs-1.2.1/bootstrap.vertical-tabs.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
  <%@ include file="/resources/html/menu.html" %>

  <div class="container">
    <div class="row">
      <div class="starter-template">
        <h1>Image Info</h1>
      </div>
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
                ${image.id}
              </td>
            </tr>
            <tr>
              <td>
                <b>Tags</b>
              </td>
              <td>
                ${image.tag}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="col-md-1"></div>
    </div>

    <br><br>
    <div class="row">
      <div class="col-xs-3"> <!-- required for floating -->
        <!-- Nav tabs -->
        <ul class="nav nav-tabs tabs-left">
          <li class="active"><a href="#run" data-toggle="tab">Run</a></li>
          <li><a href="#run_command" data-toggle="tab">Run Command</a></li>
        </ul>
      </div>

      <div class="col-xs-9">
        <!-- Tab panes -->
        <div class="tab-content">
          <div class="tab-pane active" id="run">
            <form action="${pageContext.request.contextPath}/home/images/run_image_in_container" method="POST">
              <h2>Run image in new container</h2>
              <br>
              <input type="hidden" name="imageId" value="${image.id}"/>
              <button type="submit" class="btn btn-primary">Run</button>
            </form>
          </div>
          <div class="tab-pane" id="run_command">
            <form action="${pageContext.request.contextPath}/home/images/quick_command_run" method="POST">
              <h2>Run quick command in image</h2>
              <input type="hidden" name="imageId" value="${image.id}"/>
              <input type="text" class="form-control" name="command" placeholder="Place for your command"></input>
              <br>
              <button type="submit" class="btn btn-primary">Run</button>
            </form>
          </div>
        </div>

      </div>
  </div>
</body>
</html>
