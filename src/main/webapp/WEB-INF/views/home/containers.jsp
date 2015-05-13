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

    <p class="lead">Containers</p>

    <table class="table">
      <thead>
      <tr>
        <th>Id</th>
        <th>Image</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="container" items="${containers}">
          <tr>
            <td>${container.id}</td>
            <td>${container.image}</td>
            <td><a href="${pageContext.request.contextPath}/home/containers/${container.id}">Show details</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

  </div>


  <button id="show_hide_add_container" class="btn btn-default">Show/Hide</button>
  <form role="form" class="add_container" action="${pageContext.request.contextPath}/home/containers/add_image_from_dockerfile" method="post">
    <h2>Create Image from dockerfile</h2>
    Name<br>
    <input type="text" name="image_name"/>
    <br>
    File<br>
    <textarea rows="10" cols="80" name="dockerfile"></textarea>
    <br>
    <input type="submit"/>
  </form>
  <form role="form" class="add_container" method="post">
    <div class="form-group">
      <h2>Search Image</h2>
      <label for="container_image">Image:</label>
      <input type="text" class="form-control" id="containerImage" name="containerImage" placeholder="Enter image">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

  <div class="search-result">

	<c:if test="${not empty sItems}">

	<table class="table">
      <thead>
      <tr>
        <th>Image</th>
        <th>Description</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${sItems}">
          <tr>
            <td>              <form  id="pull_container" method="post">
			    <input type="submit" class="btn btn-default" value="${item.name}" id="to_pull" name="to_pull">
			  </form>
			</td>
            <td>${item.description}</td>
            
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
    </c:if>

</div>


</body>
</html>
