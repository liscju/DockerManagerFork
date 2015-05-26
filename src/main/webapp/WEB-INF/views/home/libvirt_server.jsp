<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Libvirt server</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
  <%@ include file="/resources/html/menu.html" %>
  <div class="libvirt_server">
    <div class="starter-template">
      <h1>LibvirtManager</h1>

      <p class="lead">Server info:</p>

      <table class="table">
        <tbody>
          <c:forEach var="info" items="${server_info}">
            <tr>
              <td>${info.key}</td>
              <td>${info.value}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      
      <table class="table">
        <thead>
        <tr>
          <th>Running domains</th>
	        </tr>
        </thead>
        <tbody>
          <c:forEach var="domain" items="${domains}">
            <tr>
              <td>${domain.name}</td>
              <td><a href="${pageContext.request.contextPath}/home/domains_r/${domain.name}">Show details</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      
      <table class="table">
        <thead>
        <tr>
          <th>Defined domains</th>
	        </tr>
        </thead>
        <tbody>
          <c:forEach var="def" items="${defined}">
            <tr>
              <td>${def}</td>
              <td><a href="${pageContext.request.contextPath}/home/domains_d/${def}">Show details</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      	



  </div>
  </div>

</body>
</html>
