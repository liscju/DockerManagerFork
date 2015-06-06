<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

      <p class="lead">Domain: ${domain_name}</p>
      <a href="http://127.0.0.1:8000/${domain_name}.html" target="_blank">VNC connection</a>



  </div>
  </div>
  
  
  
  
  <div class="rdomain">
    <div class="starter-template">
      <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
        <li class="active">
          <a href="#info" data-toggle="tab">Info</a>
        </li>
        <li>
          <a href="#control" data-toggle="tab">Control</a>
        </li>
      </ul>

      <div id="my-tab-content" class="tab-content">
        <div class="tab-pane active" id="info">

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
        
        <div class="tab-pane" id="control">

			
			<form action="${pageContext.request.contextPath}/home/domain_action" method="POST">
			<input type="hidden" id="domain_name" name="domain_name" value="${domain_name}">
			  <select name="domain_action">
			    <option value="Destroy">Stop ${domain_name}</option>
			    <option value="Create">Start ${domain_name}</option>
			    <option value="Undefine">Undefine ${domain_name}</option>
			    
			  </select>
			  <input type="submit" value="Submit">
			</form>
			
						
			
          
        </div>
        
        
  </div>
  </div>
  </div>
        
  
  
  

</body>
</html>
