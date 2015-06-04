<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Libvirt server</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
</head>
<body>
  <%@ include file="/resources/html/menu.html" %>
  <div class="libvirt_server">
    <div class="starter-template">
      <h1>LibvirtManager</h1>

      <p class="lead">Running domain: ${domain_name}</p>
      <table class="table">
        <tbody>
          <c:forEach var="info" items="${rd_info}">
            <tr>
              <td>${info.key}</td>
              <td>${info.value}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>


  </div>
  </div>

</body>
</html>
