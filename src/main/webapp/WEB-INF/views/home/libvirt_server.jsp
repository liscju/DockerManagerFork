<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Libvirt server</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body class="container">
  <%@ include file="/resources/html/menu.html" %>
  <div class="libvirt_server">
    <div class="starter-template">
      <div class="row">
        <h1>LibvirtManager</h1>
      </div>

      <div class="row">
        <p class="lead">Server info:</p>
      </div>

      <div class="row">
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
      </div>

      <br><br>
      <div class="row">
        <div class="col-xs-3">
          <form method="GET" action="${pageContext.request.contextPath}/home/domains_r/create">
            <button type="submit" class="btn btn-primary pull-left">Create Domain</button>
          </form>
        </div>
        <div class="col-xs-9"></div>
      </div>

      <div class="row">
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
      </div>

      <div class="row">
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
                <td><a href="${pageContext.request.contextPath}/home/domains_r/${def}">Show details</a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="row">
        <table class="table">
          <thead>
          <tr>
            <th>Defined Storage Pools</th>
          </tr>
          </thead>
          <tbody>
            <c:forEach var="definedStoragePoolName" items="${definedStoragePoolNames}">
              <tr>
                <td>${definedStoragePoolName}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

  </div>
  </div>

</body>
</html>
