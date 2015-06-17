<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
        <p class="lead"><spring:message code="home.libvirt_server.server_info" text="missing" /></p>
      </div>

      <div class="row">
        <table class="table">
          <tbody>
            <tr>
              <td><spring:message code="home.libvirt_server.runningDefinedDomain" text="missing" /></td>
              <td>${server_info.runningDefinedDomains}</td>
            </tr>
            <tr>
              <td><spring:message code="home.libvirt_server.memory" text="missing" /></td>
              <td>${server_info.memory}</td>
            </tr>
            <tr>
              <td><spring:message code="home.libvirt_server.cores" text="missing" /></td>
              <td>${server_info.cores}</td>
            </tr>
            <tr>
              <td><spring:message code="home.libvirt_server.hostname" text="missing" /></td>
              <td>${server_info.hostname}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <br><br>
      <div class="row">
        <div class="col-xs-3">
          <form method="GET" action="${pageContext.request.contextPath}/home/domains_r/create">
            <button type="submit" class="btn btn-primary pull-left">
              <spring:message code="home.libvirt_server.create_domain" text="missing" />
            </button>
          </form>
        </div>
        <div class="col-xs-9"></div>
      </div>

      <div class="row">
        <table class="table">
          <thead>
          <tr>
            <th>
              <spring:message code="home.libvirt_server.running_domains" text="missing" />
            </th>
          </tr>
          </thead>
          <tbody>
            <c:forEach var="domain" items="${domains}">
              <tr>
                <td>${domain.name}</td>
                <td>
                  <a href="${pageContext.request.contextPath}/home/domains_r/${domain.name}">
                    <spring:message code="home.libvirt_server.show_details" text="missing" />
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="row">
        <table class="table">
          <thead>
          <tr>
            <th><spring:message code="home.libvirt_server.defined_domains" text="missing" /></th>
          </tr>
          </thead>
          <tbody>
            <c:forEach var="def" items="${defined}">
              <tr>
                <td>${def}</td>
                <td>
                  <a href="${pageContext.request.contextPath}/home/domains_r/${def}">
                    <spring:message code="home.libvirt_server.show_details" text="missing" />
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
  </div>
  </div>

</body>
</html>
