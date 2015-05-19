<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Containers</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
  <%@ include file="/resources/html/menu.html" %>
  <div class="container">
    <div class="starter-template">
      <h1>DockerManager</h1>

      <p class="lead">Images</p>

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
              <td>${image.id}</td>
              <td>${image.tag}</td>
              <td><a href="${pageContext.request.contextPath}/home/images/${image.id}">Show details</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

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

      <form role="form" action="${pageContext.request.contextPath}/home/images/find_image" method="post">
        <div class="form-group">
          <h2>Search Image</h2>
          <label for="image_to_find">Image:</label>
          <input type="text" class="form-control" id="image_to_find" name="image_to_find" placeholder="Enter image">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>

      <div class="search-result">

        <c:if test="${not empty found_images}">

          <table class="table">
            <thead>
            <tr>
              <th>Image</th>
              <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="image" items="${found_images}">
              <tr>
                <td>
                  <form  id="pull_container" method="post">
                    <input type="submit" class="btn btn-default" value="${image.key}" id="to_pull" name="to_pull">
                  </form>
                </td>
                <td>${image.value}</td>

              </tr>
            </c:forEach>
            </tbody>
          </table>

        </c:if>

      </div>

    </div>
  </div>
</body>
</html>
