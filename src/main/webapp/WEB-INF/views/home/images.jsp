<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Images</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lib/bootstrap-vertical-tabs-1.2.1/bootstrap.vertical-tabs.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/images_main.js"></script>
</head>
<body>
  <%@ include file="/resources/html/menu.html" %>
  <div class="container">
    <div class="starter-template">
      <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
        <li class="active">
          <a href="#list" data-toggle="tab">List</a>
        </li>
        <li>
          <a href="#search" data-toggle="tab">Search</a>
        </li>
        <li>
          <a href="#create" data-toggle="tab">Create</a>
        </li>
      </ul>

      <div id="my-tab-content" class="tab-content">
        <div class="tab-pane active" id="list">

          <table class="table">
            <thead>
            <tr>
              <th>Id</th>
              <th>Tag</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="image" items="${images}">
              <tr>
                <td>${fn:substring(image.id,0,10)}...</td>
                <td>${image.tag}</td>
                <td><a href="${pageContext.request.contextPath}/home/images/${image.id}">Show details</a></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="tab-pane" id="search">
          <div>
            <div class="form-group">
              <h2>Search Image</h2>
              <label for="image_to_find">Image:</label>
              <input type="text" class="form-control" id="image_to_find" name="image_to_find" placeholder="Enter image">
            </div>
            <button type="button" class="btn btn-default" onclick="searchForImage()">Search</button>
          </div>
          <br><br><br>
          <div class="search-result">
            <table class="table">
              <thead>
              <tr>
                <th>Image</th>
                <th>Description</th>
              </tr>
              </thead>
              <tbody id="search-result-body">
              </tbody>
            </table>
          </div>
        </div>
        <div class="tab-pane" id="create">
          <br><br>
          <div class="col-xs-3"> <!-- required for floating -->
            <!-- Nav tabs -->
            <ul class="nav nav-tabs tabs-left">
              <li class="active"><a href="#dockerfile" data-toggle="tab">Dockerfile</a></li>
              <li><a href="#war" data-toggle="tab">War</a></li>
            </ul>
          </div>

          <div class="col-xs-9">
            <!-- Tab panes -->
            <div class="tab-content">
              <div class="tab-pane active" id="dockerfile">
                <form role="form" action="${pageContext.request.contextPath}/home/images/add_image_from_dockerfile" method="post">
                  <h2>Create Image from dockerfile</h2>
                  Name<br>
                  <input type="text" name="image_name"/>
                  <br>
                  File<br>
                  <textarea rows="10" cols="80" name="dockerfile"></textarea>
                  <br>
                  <input type="submit"/>
                </form>
              </div>
              <div class="tab-pane" id="war">
                <form role="form" action="${pageContext.request.contextPath}/home/images/create_image_for_war"
                      method="post" enctype="multipart/form-data">
                  <h2>Create image for given WAR</h2>
                  Name<br>
                  <input type="text" name="image_name"/>
                  <br><br>
                  War FILE<br>
                  <input type="file" name="war_file"/>
                  <br>
                  <input type="submit"/>
                </form>
              </div>
            </div>

          </div>

        </div>
      </div>
    </div>
  </div>
</body>
</html>
