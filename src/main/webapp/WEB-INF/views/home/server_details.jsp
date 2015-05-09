<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DockerManager Servers</title>
  <%@ include file="/resources/html/includes.html" %>
  <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/containers_main.js"></script>
</head>
<body>
<%@ include file="/resources/html/menu.html" %>

<div class="server">
  <div class="starter-template">
    <h1>DockerManager</h1>

    <p class="lead">Server id: ${serverID}, name: ${name}, address: ${address}</p>
	${error}	
	
	<form role="form" id="change_server" method="post">
	  	<select name="action">
		    <option value="delete">delete server</option>
		    <option value="2">2</option>
		    <option value="3">3</option>
	  	</select>
	  	<input type="submit" value="Submit">
	</form>
	
	<table class="table">
      <thead>
      <tr>
        <th>Parameter</th>
        <th>Value</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${items}">
          <tr>
            <td>${item.parameter}</td>
            <td>${item.value}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
  </div>

</div>

</body>
</html>
